package com.sq.lib_common.exception;

import java.io.File;

/**
 * 异常接口
 * <p>
 * Created by javakam on 2018/5/29.
 */
public interface CrashListener {
    /**
     * 将崩溃日志传到目标层。比如服务器。
     *
     * @param logFile
     */
    void sendLogFileToTarget(File logFile);

    /**
     * 响应系统崩溃，关闭app
     *
     * @param thread
     * @param ex
     */
    void closeApp(Thread thread, Throwable ex);
}
