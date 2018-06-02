package com.sq.withyou;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sq.withyou.main.MainActivity;

/**
 * 路由
 * <p>
 * 一些常用的页面跳转
 * <p>
 * https://github.com/raee/android-cnblogs/blob/f5026cc4706c8cb5022e555171dba6debd7a7238/app/src/main/java/com/rae/cnblogs/AppRoute.java
 *
 * @author javakam
 * @date 2018-6-2 18:29:37
 */
public final class AppRoute {

    // WEB 登录
    public static final int REQ_CODE_WEB_LOGIN = 100;
    // 注册
    public static final int REQ_CODE_WEB_REGISTER = 101;
    // 首页
    public static final int REQ_CODE_HOME = 102;


    private static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    private static void startActivity(Context context, Class<?> cls) {
        startActivity(context, new Intent(context, cls));
    }

    private static void startActivityForResult(Activity context, Intent intent, int requestCode) {
        context.startActivityForResult(intent, requestCode);
    }

    private static void startActivityForResult(Activity context, Class<?> cls, int requestCode) {
        startActivityForResult(context, new Intent(context, cls), requestCode);
    }

    /**
     * 博客正文界面
     *
     * @param blogId 博客ID
     * @param type   博客类型
     */
//    public static void jumpToBlogContent(Context context, String blogId, BlogType type) {
//        Intent intent = new Intent(context, BlogContentActivity.class);
//        intent.putExtra("blogId", blogId);
//        intent.putExtra("type", type.getTypeName());
//        startActivity(context, intent);
//    }

    /**
     * 博客正文界面
     *
     * @param blog 博客实体
     * @param type 博客类型
     */
//    public static void jumpToBlogContent(Context context, BlogBean blog, BlogType type) {
//        if (blog == null) {
//            return;
//        }
//        Intent intent = new Intent(context, BlogContentActivity.class);
//        // 不传递摘要和正文这些过大的数据。进去博文正文之后再从数据库拉取。已经在BlogBean里面处理大数据问题
//        intent.putExtra("blog", blog);
//        intent.putExtra("blogId", blog.getBlogId());
//        intent.putExtra("type", type.getTypeName());
//        startActivity(context, intent);
//    }

    /**
     * 网页
     *
     * @param url 路径
     */
//    public static void jumpToWeb(Context context, String url) {
//        Intent intent = new Intent(context, WebActivity.class);
//        intent.setData(Uri.parse(url));
//        startActivity(context, intent);
//    }

    /**
     * 主界面
     */
    public static void jumpToMain(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 登录
     */
    public static void jumpToLogin(Context context) {
//        startActivity(context, LoginActivity.class);
    }

    /**
     * 登录，有回调结果
     */
    public static void jumpToWebLogin(Activity context) {
//        startActivityForResult(context, WebLoginActivity.class, REQ_CODE_WEB_LOGIN);
    }


    /**
     * 设置
     */
    public static void jumpToSetting(Context context) {
//        startActivity(context, SettingActivity.class);
    }

    /**
     * 关于我
     */
    public static void jumpToAboutMe(Context context) {
//        startActivity(context, AboutMeActivity.class);
    }

}
