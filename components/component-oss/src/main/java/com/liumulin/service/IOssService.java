package com.liumulin.service;

import com.liumulin.constant.OssSavePlaceEnum;
import org.springframework.web.multipart.MultipartFile;

/**
 * OSSService 接口
 *
 * @author terrfly
 * @site https://www.jeequan.com
 */
public interface IOssService {

    /**
     * 上传文件 & 生成下载/预览URL
     **/
    String upload2PreviewUrl(OssSavePlaceEnum ossSavePlaceEnum, MultipartFile multipartFile, String saveDirAndFileName);

    /**
     * 将文件下载到本地
     * 返回是否 写入成功
     * false: 写入失败， 或者文件不存在
     **/
    boolean downloadFile(OssSavePlaceEnum ossSavePlaceEnum, String source, String target);

}
