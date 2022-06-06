package com.liumulin.ctrl;

import cn.hutool.core.lang.UUID;
import com.liumulin.beans.CommonResult;
import com.liumulin.exceptions.CustomException;
import com.liumulin.exceptions.ServiceException;
import com.liumulin.temp.SystemErrorType;
import com.liumulin.utils.FileUtil;
import com.liumulin.model.OssFileConfig;
import com.liumulin.service.IOssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/*
 * 统一文件上传接口（ossFile）
 */
@Slf4j
@RestController
@RequestMapping("/api/ossFiles")
//public class OssFileController extends AbstractCtrl {
public class OssFileController {

    @Autowired
    private IOssService ossService;

    /**
     * 上传文件 （单文件上传）
     */
    @PostMapping("/{bizType}")
    public CommonResult singleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable("bizType") String bizType) {

        if (file == null) {
//            return ApiRes.fail(ApiCodeEnum.SYSTEM_ERROR, "选择文件不存在");
            throw new CustomException(SystemErrorType.UPLOAD_FILE_SIZE_LIMIT);
        }
        try {

            OssFileConfig ossFileConfig = OssFileConfig.getOssFileConfigByBizType(bizType);

            //1. 判断bizType 是否可用
            if (ossFileConfig == null) {
//                throw new BizException("类型有误");
            }

            // 2. 判断文件是否支持
            String fileSuffix = FileUtil.getFileSuffix(file.getOriginalFilename(), false);
            if (!ossFileConfig.isAllowFileSuffix(fileSuffix)) {
//                throw new BizException("上传文件格式不支持！");
                throw new CustomException("上传文件格式不支持！");
            }

            // 3. 判断文件大小是否超限
            if (!ossFileConfig.isMaxSizeLimit(file.getSize())) {
//                throw new BizException("上传大小请限制在[" + ossFileConfig.getMaxSize() / 1024 / 1024 + "M]以内！");
            }

            // 新文件地址 (xxx/xxx.jpg 格式)
            String saveDirAndFileName = bizType + "/" + UUID.fastUUID() + "." + fileSuffix;
            String url = ossService.upload2PreviewUrl(ossFileConfig.getOssSavePlaceEnum(), file, saveDirAndFileName);

            return CommonResult.ok(url);
//            return ApiRes.ok(url);

        } catch (ServiceException exc) {
            throw exc;
        } catch (Exception e) {
            log.error("upload error, fileName = {}", file.getOriginalFilename(), e);
//            throw new BizException(ApiCodeEnum.SYSTEM_ERROR);
            throw new CustomException(SystemErrorType.SYSTEM_ERROR);
        }
    }

}
