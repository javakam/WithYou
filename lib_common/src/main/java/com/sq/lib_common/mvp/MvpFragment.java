package com.sq.lib_common.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sq.lib_common.base.BaseFragment;

public abstract class MvpFragment<P extends IPresenter> extends BaseFragment implements BaseView {
    protected P mPresenter;

    /**
     * 初始化presenter层
     */
    protected abstract void initPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mView = null;
        mPresenter = null;
    }

}
