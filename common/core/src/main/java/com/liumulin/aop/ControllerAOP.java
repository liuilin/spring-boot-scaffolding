package com.liumulin.aop;

import com.liumulin.beans.CommonResult;
import com.liumulin.exceptions.CommonResultCode;
import com.liumulin.exceptions.CustomException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 处理和包装异常
 *
 * @author liuqiang
 */
//@Slf4j
@Aspect
@Component
@Order(-99)
public abstract class ControllerAOP {
    @Pointcut("execution(public com.liumulin.beans.CommonResult *(..))")
    public void controllerMethod() {
    }

    @Around("controllerMethod()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        CommonResult<?> result;

        try {
            result = (CommonResult<?>) pjp.proceed();
//            log.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }

        return result;
    }

//    private IErrorMsg handlerException(ProceedingJoinPoint pjp, Throwable ex) {
//        IErrorMsg result = this.createResult();
//
//        // 已知异常【注意：已知异常不要打印堆栈，否则会干扰日志】
//        // 校验出错，参数非法
//        if (ex instanceof CheckException || ex instanceof IllegalArgumentException) {
//            result.setMsg(ex.getLocalizedMessage());
//            result.setCode(CommonResult.CHECK_FAIL);
//        }
//        // 没有登陆
//        else if (ex instanceof NoLoginException) {
//            result.setMsg("Unlogin");
//            result.setCode(CommonResult.NO_LOGIN);
//        }
//        // 没有权限
//        else if (ex instanceof NoPermissionException) {
//            result.setMsg("NO PERMISSION");
//            result.setCode(CommonResult.NO_PERMISSION);
//        } else {
//            log.error(pjp.getSignature() + " error ", ex);
//
//            // TODO 未知的异常，应该格外注意，可以发送邮件通知等
//            result.setMsg(ex.toString());
//            result.setCode(CommonResult.UNKNOWN_EXCEPTION);
//        }
//
//        return result;
//    }

    private CommonResult<?> handlerException(ProceedingJoinPoint pjp, Throwable ex) {
        CommonResult result = new CommonResult<>();

        // 已知异常【注意：已知异常不要打印堆栈，否则会干扰日志】
        // 校验出错，参数非法
        if (ex instanceof CustomException) {
//            result.setMsg(ex.getLocalizedMessage());
//            result.setCode(CommonResult.CHECK_FAIL);
            result = new CommonResult<>(CommonResultCode.BAD_REQUEST);
        }else {
            result = new CommonResult(CommonResultCode.SERVER_ERROR);
        }

        return result;
    }

}
