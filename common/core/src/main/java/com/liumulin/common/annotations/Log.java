package com.liumulin.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 日志信息
 *
 * @author liuqiang
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    // 操作类型
    String action();

    // 对象类型
    String itemType() default "";

    // 对象id
    String itemId() default "";

    // （其他）参数
    String param() default "";
}
