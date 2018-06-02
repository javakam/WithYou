package com.sq.withyou;


import com.sq.lib_common.base.BaseApplication;

/**
 * Created by javakam on 2018/5/23.
 */
public class App extends BaseApplication {
    private static App instance=new App();

    public static synchronized App getInstance() {
        return instance;
    }

//    static {
//        AppCompatDelegate.setDefaultNightMode(
//                AppCompatDelegate.MODE_NIGHT_NO);
//    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
