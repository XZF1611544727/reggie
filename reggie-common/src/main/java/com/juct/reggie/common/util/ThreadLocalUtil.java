package com.juct.reggie.common.util;

public class ThreadLocalUtil {

    //创建一个ThreadLocal对象
    private static ThreadLocal<Object> th = new ThreadLocal<Object>();

    // 存放
    public static void set(Object obj) {
        th.set(obj);
    }

    // 获取
    public static Object get() {
        return th.get();
    }

    // 移除
    public static void remove() {
        th.remove();
    }
}
