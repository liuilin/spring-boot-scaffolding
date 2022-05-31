package com.liumulin.common.aop;

import com.liumulin.common.beans.CommonResult;
import com.liumulin.common.exceptions.NoLoginException;
import com.liumulin.common.exceptions.CheckException;
import com.liumulin.common.exceptions.NoPermissionException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * 处理和包装异常
 *
 * @author liuqiang
 */
@Slf4j
public abstract class ControllerAOP {
    protected abstract void targetMethod();

    @Around("targetMethod()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        try {
            Object result = (CommonResult<?>) pjp.proceed();

            // 如果需要打印入参，可以从这里取出打印
            // Object[] args = pjp.getArgs();

            // 本次操作用时（毫秒）
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);

            return result;
        } catch (Throwable e) {
            return handlerException(pjp, e);
        }
    }

    private IErrorMsg handlerException(ProceedingJoinPoint pjp, Throwable ex) {
        IErrorMsg result = this.createResult();

        // 已知异常【注意：已知异常不要打印堆栈，否则会干扰日志】
        // 校验出错，参数非法
        if (ex instanceof CheckException || ex instanceof IllegalArgumentException) {
            result.setMsg(ex.getLocalizedMessage());
            result.setCode(CommonResult.CHECK_FAIL);
        }
        // 没有登陆
        else if (ex instanceof NoLoginException) {
            result.setMsg("Unlogin");
            result.setCode(CommonResult.NO_LOGIN);
        }
        // 没有权限
        else if (ex instanceof NoPermissionException) {
            result.setMsg("NO PERMISSION");
            result.setCode(CommonResult.NO_PERMISSION);
        } else {
            log.error(pjp.getSignature() + " error ", ex);

            // TODO 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMsg(ex.toString());
            result.setCode(CommonResult.UNKNOWN_EXCEPTION);
        }

        return result;
    }

    protected abstract IErrorMsg createResult();
}
