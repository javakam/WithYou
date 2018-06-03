package com.sq.lib_common;

/**
 * Created by javakam on 2018/6/2.
 */
public class Global {
    /**
     * Log Tag
     */
    public static final String TAG = "123";

    /*=====================================Cache========================================*/
    public static final int LRUCACHE_MAX_SIZE = (int) (Runtime.getRuntime().maxMemory() / 1024);
}
