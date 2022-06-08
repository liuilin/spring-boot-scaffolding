package com.liumulin.aop;

import com.alibaba.fastjson2.JSONObject;
import com.liumulin.annotations.Log;
import com.liumulin.utils.SPELUtil;
import com.liumulin.utils.User;
import com.liumulin.utils.UserUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 日志信息处理AOP
 * 把需要的信息从参数中提取出来，转成json字符串放到MDC中使用。
 * <p>
 * 注意：注解不支持重入（就是嵌套的方法里面还有LOG注解），因为我觉得不需要嵌套
 * 如果你项目中有这种使用场景，自己修改一下也非常简单，就是修改前保存起来即可。
 *
 * @author 晓风轻 https://github.com/xwjie/ElementVueSpringbootCodeTemplate
 */
@Order(-100)
@Component
@Aspect
@Slf4j
public class LogAOP {

    private static final String JSON_KEY = "logjson";

    @Pointcut("@annotation(com.liumulin.annotations.Log)")
    public void logMethod() {

    }

    @Around("logMethod()")
    @SneakyThrows
    public Object handleLogMethod(ProceedingJoinPoint pjp) {
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

        JSONObject json = new JSONObject();

        // 使用单字母而不是全名，是为了节省日志文件大小。

        // 用户
        User user = UserUtil.getUserIfLogin();

        if (user != null) {
//            json.put("U", user.getName());
            json.put("U", "testUsername");
        }

        // 操作
        json.put("A", logAnnotation.action());

        // 对象类型
        json.put("T", logAnnotation.itemType());
        // 对象 id，spel 表达式
        json.put("I", spel.cacl(logAnnotation.itemId()));

        // 其他参数，spel 表达式
        json.put("P", spel.cacl(logAnnotation.param()));

        MDC.put(JSON_KEY, json.toJSONString());
    }
}
