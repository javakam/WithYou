package com.sq.lib_common.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sq.lib_common.base.BaseActivity;

/**
 * @author javakam
 * @date 2018-6-2 20:30:54
 */
public abstract class MvpActivity<P extends IPresenter> extends BaseActivity implements BaseView {
    protected P mPresenter;

    /**
     * 初始化presenter层
     */
    protected abstract void initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        initViews(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        //恢复离线和在线系统标识
        if (savedInstanceState != null && mPresenter != null) {
            mPresenter.setLocal(savedInstanceState.getBoolean("is_local_flag"));
        }
        if (isOpenStatusBar()) {
//            StatusBarCompat.compat(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mDisposable != null && !mDisposable.isDisposed())
//            mDisposable.dispose();
        //防止内存泄露
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        mView = null;
    }
}
