package com.liumulin.common.aop;

import com.liumulin.common.beans.CommonResult;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author liuqiang
 * @Description Controller AOP 处理异常并打印调用日志。
 * @Date 2020/12/20
 */
@Aspect
@Component
public class CommonResultControllerAOP extends ControllerAOP {

    @Override
    @Pointcut("execution(public com.liumulin.common.beans.CommonResult *(..)))")
    public void targetMethod() {
    }

    @Override
    protected IErrorMsg createResult() {
        return new CommonResult<>();
    }
}
