package com.liumulin.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 工具类的注入
 *
 * @author liuqiang
 */
@Slf4j
@Component
public class StaticFieldInjectionConfiguration {

    @Autowired
    MessageSource resources;

    @PostConstruct
    private void init() {
        log.info("\n\n-----StaticFieldInjectionConfiguration----");
        CheckUtil.setResources(resources);
    }
}