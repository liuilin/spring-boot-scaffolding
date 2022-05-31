package com.liumulin.common.utils;

import java.util.Collection;

/**
 * 工具类的规范例子
 *
 * @author liuqiang
 */
public class StringUtil {

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }
}
