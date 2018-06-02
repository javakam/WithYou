package com.sq.lib_common.exception;

/**
 * 异常处理基类
 * <p>
 * Created by javakam on 2018/5/29.
 */
public abstract class BaseCrashReport implements CrashListener {

    /**
     * 系统默认的异常处理（默认情况下，系统会终止当前的异常程序）
     */
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    public BaseCrashReport() {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    /**
     * 关闭app
     *
     * @param thread
     * @param ex
     */
    @Override
    public void closeApp(Thread thread, Throwable ex) {
        mDefaultCrashHandler.uncaughtException(thread, ex);
    }
}
