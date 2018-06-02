package com.sq.lib_common.base;

import android.app.Application;

import com.sq.lib_common.exception.CrashManager;
import com.sq.lib_common.exception.HttpCrashReport;
import com.sq.lib_common.utils.SharePrefUtil;

/**
 * @author javakam
 * @date 2018-6-2 20:42:35
 */
public abstract class BaseApplication extends Application {
    private static Application app;
    public static String baseUrl = "http://www.wanandroid.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化自己的全局配置
        app = this;
        //初始化SharedPreferences配置文件
        initSharePrefUtil();
        //异常处理
        initCrashManage();
    }

    public static Application getApp() {
        return app;
    }

    private void initSharePrefUtil() {
        SharePrefUtil.initSharePreference(app);
    }

    private void initCrashManage() {
        CrashManager crashManager = CrashManager.getInstance();
        HttpCrashReport httpCrashReport = new HttpCrashReport();
        crashManager.init(app, httpCrashReport);
    }
}
