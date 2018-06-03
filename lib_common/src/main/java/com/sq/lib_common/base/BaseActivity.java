package com.sq.lib_common.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.sq.lib_common.utils.UiUtil;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by javakam on 2018/6/2.
 */
public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 系统DecorView的根View
     */
    protected View mView;
    private Unbinder mUnbinder;

    /**
     * 是否退出App
     */
    private static Boolean isExit = false;

    @LayoutRes
    protected abstract int getContentId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //去掉原生的ActionBar--必须在super之前调用
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        int layoutId = getContentId();
        if (layoutId > 0) {
            setContentView(getContentId());
            mView = findViewById(android.R.id.content);
            mUnbinder = ButterKnife.bind(this);
        }
        initVariables();
    }

    /**
     * 设置ToolBar
     * <p>
     * 设置标题setText(title) 必须在 setSupportActionBar 之前才有效
     *
     * @param toolbarId
     * @param toolBarTitleId
     * @param title
     */
    protected void setUpToolBar(@IdRes int toolbarId, @IdRes int toolBarTitleId, @Nullable String title) {
        Toolbar toolbar = findViewById(toolbarId);
        TextView toolBarTitle = toolbar.findViewById(toolBarTitleId);
        toolBarTitle.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        toolBarTitle.setText(title);
        setSupportActionBar(toolbar);
        //设置返回键可用
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

    protected abstract void initVariables();

    protected abstract void initViews(Bundle savedInstanceState);

    /**
     * 设置是否显示状态栏
     */
    protected boolean isOpenStatusBar() {
        return true;
    }

    /**
     * 显示提示信息
     *
     * @param message
     */
    public void showMessage(String message) {
        if (mView != null) {
            UiUtil.snackbarLong(mView, message);
            return;
        }
        UiUtil.toastShort(this, message);
    }

    /**
     * 连续点击两次退出App
     */
    public void exitBy2Click() {
        if (!isExit) {
            // 准备退出
            isExit = true;
            showMessage("再按一次退出");
            // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
            Flowable.timer(2000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                    .subscribe(aLong -> isExit = false);
        } else {
            finish();
        }
    }
}
