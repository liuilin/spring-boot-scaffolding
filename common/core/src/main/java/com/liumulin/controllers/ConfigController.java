package com.liumulin.controllers;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.liumulin.common.annotations.Log;
import com.liumulin.common.beans.CommonResult;
import com.liumulin.common.consts.LogConst;
import com.liumulin.entity.Config;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.liumulin.services.ConfigService;

import java.util.Collection;


/**
 * 配置对象处理器
 *
 * @author liuqiang
 */
@Api(description = "配置管理")
@RequestMapping("/config")
@RestController
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping("/all")
    @Log(action = LogConst.ACTION_QUERY, itemType = LogConst.ITEM_TYPE_CONFIG)
    public CommonResult<Collection<Config>> getAll() {
        return new CommonResult<>(configService.getAll());
    }

    @GetMapping("/count")
    @Log(action = LogConst.ACTION_QUERY, itemType = LogConst.ITEM_TYPE_CONFIG)
    public CommonResult<Long> count() {
        return new CommonResult<>(configService.count());
    }

    /**
     * 新增数据, 返回新对象的id
     */
    @ApiOperation("添加配置")
    @ApiOperationSupport(ignoreParameters = {"com.liumulin.config.id", "com.liumulin.config.createTime", "com.liumulin.config.updateTime", "com.liumulin.config.creator"})
    @PostMapping("/add")
    @Log(action = LogConst.ACTION_ADD, itemType = LogConst.ITEM_TYPE_CONFIG, itemId = "#com.liumulin.config.name")
    public CommonResult<Long> add(@RequestBody Config config) {
        return new CommonResult<>(configService.add(config));
    }

    /**
     * 根据id删除对象
     */
    @PostMapping("/delete")
    @Log(action = LogConst.ACTION_DELETE, itemType = LogConst.ITEM_TYPE_CONFIG, itemId = "#id")
    public CommonResult<Boolean> delete(int id) {
        return new CommonResult<>(configService.delete(id));
    }

    @PostMapping("/update")
    @Log(action = LogConst.ACTION_UPDATE, itemType = LogConst.ITEM_TYPE_CONFIG, itemId = "#com.liumulin.config.name", param = "#com.liumulin.config")
    public CommonResult<Boolean> update(Config config) {
        configService.update(config);
        return new CommonResult<>(true);
    }
}
