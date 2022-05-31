package com.liumulin.common.aop;

import com.liumulin.common.utils.JSONUtil;
import com.liumulin.common.utils.SPELUtil;
import com.liumulin.common.utils.UserUtil;
import com.liumulin.common.annotations.Log;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 日志信息处理 AOP
 * 把需要的信息从参数中提取出来，转成 json 字符串放到 MDC 中使用。
 * <p>
 * 注意：注解不支持重入（就是嵌套的方法里面还有 LOG 注解），因为我觉得不需要嵌套
 * 如果你项目中有这种使用场景，自己修改一下也非常简单，就是修改前保存起来即可。
 *
 * @author liuqiang
 */
@Slf4j
@Aspect
public class LogAOP {

    private static final String JSON_KEY = "logJson";

    @Pointcut("@annotation(com.liumulin.common.annotations.Log)")
    public void targetMethod() {
    }

    @SneakyThrows
    @Around("targetMethod()")
    public Object handlerLogMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        Object result;

        try {
            putLogInfo2MDC(pjp);

            result = pjp.proceed();

            // 本次操作用时（毫秒）
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);
        } finally {
            clearMDC();
        }

        return result;
    }

    private void clearMDC() {
        MDC.remove(JSON_KEY);
    }

    private void putLogInfo2MDC(ProceedingJoinPoint pjp) {
        // 得到方法上的注解
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        Log logAnnotation = signature.getMethod().getAnnotation(Log.class);

        SPELUtil spel = new SPELUtil(pjp);

        Map<String, Object> loginfoMap = new LinkedHashMap<>();

        // 使用单字母而不是全名，是为了节省日志文件大小。

        // 用户
        loginfoMap.put("U", UserUtil.getUserIfLogin());

        // 操作
        loginfoMap.put("A", logAnnotation.action());

        // 对象类型
        loginfoMap.put("T", logAnnotation.itemType());

        // 对象id，spel表达式
        loginfoMap.put("I", spel.cacl(logAnnotation.itemId()));

        // 其他参数，spel表达式
        loginfoMap.put("P", spel.cacl(logAnnotation.param()));

        MDC.put(JSON_KEY, JSONUtil.toJsonStr(loginfoMap));
    }
}
