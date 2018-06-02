/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sq.lib_common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * ================================================
 * 一些框架常用的工具:
 * <p>
 * Created by JavaKam on 2018-6-2 18:24:45.
 * ================================================
 */
public class UiUtil {
    private static Toast mToast;
    private static Snackbar mSnackbar;

    private UiUtil() {
        throw new IllegalStateException("u can't instantiate me!");
    }

    /**
     * 设置hint大小
     *
     * @param size
     * @param v
     * @param res
     */
    public static void setViewHintSize(Context context, int size, TextView v, int res) {
        SpannableString ss = new SpannableString(getResources(context).getString(
                res));
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
        // 附加属性到文本  
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置hint  
        v.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
    }


    /**
     * dip转pix
     *
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = getResources(context).getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * get Resources
     */
    public static Resources getResources(Context context) {
        return context.getResources();
    }

    /**
     * get string-array from res
     *
     * @param context
     * @param id
     * @return String[]
     */
    public static String[] getStringArray(Context context, int id) {
        return getResources(context).getStringArray(id);
    }

    /**
     * get string-array list from res
     *
     * @param context
     * @param id
     * @return List<String>
     */
    public List<String> getStringArrayList(Context context, @ArrayRes int id) {
        return Arrays.asList(getStringArray(context, id));
    }

    /**
     * pix转dip
     */
    public static int pix2dip(Context context, int pix) {
        final float densityDpi = getResources(context).getDisplayMetrics().density;
        return (int) (pix / densityDpi + 0.5f);
    }

    /**
     * 从 dimens 中获得尺寸
     *
     * @param context
     * @param id
     * @return
     */
    public static int getDimens(Context context, int id) {
        return (int) getResources(context).getDimension(id);
    }

    /**
     * 从 dimens 中获得尺寸
     *
     * @param context
     * @param dimenName
     * @return
     */
    public static float getDimens(Context context, String dimenName) {
        return getResources(context).getDimension(getResources(context).getIdentifier(dimenName, "dimen", context.getPackageName()));
    }

    /**
     * 从String 中获得字符
     *
     * @return
     */

    public static String getString(Context context, int stringID) {
        return getResources(context).getString(stringID);
    }

    /**
     * 从String 中获得字符
     *
     * @return
     */

    public static String getString(Context context, String strName) {
        return getString(context, getResources(context).getIdentifier(strName, "string", context.getPackageName()));
    }

    /**
     * findview
     *
     * @param view
     * @param viewName
     * @param <T>
     * @return
     */
    public static <T extends View> T findViewByName(Context context, View view, String viewName) {
        int id = getResources(context).getIdentifier(viewName, "id", context.getPackageName());
        T v = (T) view.findViewById(id);
        return v;
    }

    /**
     * findview
     *
     * @param activity
     * @param viewName
     * @param <T>
     * @return
     */
    public static <T extends View> T findViewByName(Context context, Activity activity, String viewName) {
        int id = getResources(context).getIdentifier(viewName, "id", context.getPackageName());
        T v = (T) activity.findViewById(id);
        return v;
    }

    /**
     * 根据 layout 名字获得 id
     *
     * @param layoutName
     * @return
     */
    public static int findLayout(Context context, String layoutName) {
        int id = getResources(context).getIdentifier(layoutName, "layout", context.getPackageName());
        return id;
    }

    /**
     * 填充view
     *
     * @param detailScreen
     * @return
     */
    public static View inflate(Context context, int detailScreen) {
        return View.inflate(context, detailScreen, null);
    }

    /**
     * Toast Short
     *
     * @param str
     */
    public static void toastShort(Context context, String str) {
        toastText(context, str, Toast.LENGTH_SHORT);
    }

    /**
     * Toast Long
     *
     * @param str
     */
    public static void toastLong(Context context, String str) {
        toastText(context, str, Toast.LENGTH_LONG);
    }

    private static void toastText(Context context, String str, int duration) {
        if (str == null || str.trim().length() == 0) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(context, str, duration);
        } else {
            mToast.setText(str);
        }
        mToast.show();
    }

    /**
     * 使用 {@link Snackbar} 长时间显示文本消息
     *
     * @param view
     * @param text
     */
    public static void snackbarLong(View view, String text) {
        snackbarTextWithDuration(view, text, Snackbar.LENGTH_LONG);
    }

    /**
     * 使用 {@link Snackbar} 短时间显示文本消息
     *
     * @param view
     * @param text
     */
    public static void snackbarTextWithShort(View view, String text) {
        snackbarTextWithDuration(view, text, Snackbar.LENGTH_SHORT);
    }

    private static void snackbarTextWithDuration(View view, String text, int duration) {
        if (text == null || text.trim().length() == 0) {
            return;
        }
        if (mSnackbar == null && !mSnackbar.isShown()) {
            mSnackbar = Snackbar.make(view, text, duration);
        }
        mSnackbar.show();
    }

    /**
     * 通过资源id获得drawable
     *
     * @param rID
     * @return
     */
    public static Drawable getDrawablebyResource(Context context, int rID) {
        return getResources(context).getDrawable(rID);
    }

    /**
     * 跳转界面 3
     *
     * @param activity
     * @param homeActivityClass
     */
    public static void startActivity(Activity activity, Class homeActivityClass) {
        Intent intent = new Intent(activity, homeActivityClass);
        activity.startActivity(intent);
    }

    /**
     * 跳转界面 4
     *
     * @param
     */
    public static void startActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        return getResources(context).getDisplayMetrics().widthPixels;
    }

    /**
     * 获得屏幕的高度
     *
     * @return
     */
    public static int getScreenHeidth(Context context) {
        return getResources(context).getDisplayMetrics().heightPixels;
    }


    /**
     * 获得颜色
     */
    public static int getColor(Context context, int rid) {
        return getResources(context).getColor(rid);
    }

    /**
     * 获得颜色
     */
    public static int getColor(Context context, String colorName) {
        return getColor(context, getResources(context).getIdentifier(colorName, "color", context.getPackageName()));
    }

    /**
     * 从父布局中移除该view
     *
     * @param view
     */
    public static void removeChild(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(view);
        }
    }


    /**
     * 全屏,并且沉侵式状态栏
     *
     * @param activity
     */
    public static void statuInScreen(Activity activity) {
        WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setAttributes(attrs);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    /**
     * 配置 RecyclerView
     *
     * @param recyclerView
     * @param layoutManager
     */
    public static void configRecyclerView(final RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 清除公共空控件的数据
     */
    public static void clearCommonUI(View... views) {
        for (View view : views) {
            if (view == null) {
                continue;
            }
            if (CheckBox.class.isInstance(view)) {
                CheckBox cb = (CheckBox) view;
                cb.setChecked(false);
            } else if (TextView.class.isInstance(view)) {
                TextView tv = (TextView) view;
                tv.setText("");
            } else if (Spinner.class.isInstance(view)) {
                Spinner sp = (Spinner) view;
                if (sp.getAdapter() != null) {
                    sp.setSelection(0);
                }
            }
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     * @param view
     */
    public static void hideKeyboard(Activity activity, View view) {
        if (view == null) {
            view = activity.getCurrentFocus();
        }
        if (view != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
