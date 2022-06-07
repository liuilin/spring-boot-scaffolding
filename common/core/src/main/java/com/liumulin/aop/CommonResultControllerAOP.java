//package com.liumulin.aop;
//
//import com.liumulin.exceptions.ResultCode;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
///**
// * @author liuqiang
// * @Description Controller AOP 处理异常并打印调用日志。
// * @Date 2020/12/20
// */
//@Aspect
//@Component
//public class CommonResultControllerAOP extends ControllerAOP {
//
//    @Override
//    @Pointcut("execution(public com.liumulin.beans.CommonResult *(..)))")
//    public void targetMethod() {
//    }
//
//    @Override
//    protected ResultCode createResult() {
////        return new CommonResult();
//        return null;
//    }
//}
