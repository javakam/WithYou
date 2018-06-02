package com.sq.lib_common.exception;

import java.io.File;

/**
 * 将崩溃日志上传到服务器
 * <p>
 * Created by javakam on 2018/5/29.
 */
public class HttpCrashReport extends BaseCrashReport {

    public HttpCrashReport() {
    }


    @Override
    public void sendLogFileToTarget(final File logFile) {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
