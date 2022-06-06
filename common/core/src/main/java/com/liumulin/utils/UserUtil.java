package com.liumulin.utils;


import com.liumulin.temp.NoLoginException;
import entity.User;
import org.slf4j.MDC;

import java.util.Locale;

/**
 * 用户工具类
 *
 * @author liuqiang
 */
public class UserUtil {

    public static final String KEY_LANG = "lang";
    public static final String KEY_USER = "user";
    private final static ThreadLocal<User> tlUser = new ThreadLocal<>();
    private final static ThreadLocal<Locale> tlLocale = ThreadLocal.withInitial(() -> {
        // 语言的默认值
        return Locale.CHINESE;
    });

    /**
     * 如果没有登录，返回null
     */
    public static User getUserIfLogin() {
        return tlUser.get();
    }

    /**
     * 如果没有登录会抛出异常
     */
    public static User getUser() {
        User user = tlUser.get();

        if (user == null) {
            throw new NoLoginException();
        }

        return user;
    }

    public static void setUser(User user) {
        tlUser.set(user);

        // 把用户信息放到log4j
        MDC.put(KEY_USER, user.getUsername());
    }

    public static Locale getLocale() {
        return tlLocale.get();
    }

    public static void setLocale(String locale) {
        setLocale(new Locale(locale));
    }

    public static void setLocale(Locale locale) {
        tlLocale.set(locale);
    }

    public static void clearAllUserInfo() {
        tlUser.remove();
        tlLocale.remove();

        MDC.remove(KEY_USER);
    }
}
