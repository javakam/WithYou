package com.sq.lib_common.utils;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.view.View;

import com.sq.lib_common.Global;


/**
 * Utils初始化相关
 *
 * @author javakam
 * @date 2018-6-2 16:41:01Utils初始化相关
 */
public class CacheUtil {

    private static Context context;
    private static LruCache lruCache;
    private static ACache mCache;

    private CacheUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        CacheUtil.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }

    public static void initCache() {
        mCache = ACache.get(context);
        lruCache = new LruCache<>(Global.LRUCACHE_MAX_SIZE);
    }

    /**
     * 获取LruCache
     *
     * @return
     */
    public static LruCache getLruCache() {
        return lruCache;
    }

    /**
     * 获得缓存ACache
     *
     * @return
     */
    public static ACache getAcache() {
        return mCache;
    }

    /**
     * View获取Activity的工具
     *
     * @param view view
     * @return Activity
     */
    public static Activity getActivity(@NonNull View view) {
        Context context = view.getContext();

        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }

        throw new IllegalStateException("View " + view + " is not attached to an Activity");
    }

    /**
     * 全局获取String的方法
     *
     * @param id 资源Id
     * @return String
     */
    public static String getString(@StringRes int id) {
        return context.getResources().getString(id);
    }


    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }
}