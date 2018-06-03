package com.sq.withyou.main;

import android.content.Context;

import com.sq.lib_common.mvp.BasePresenter;

/**
 * @author javakam
 * @date 2018/6/3
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(Context context) {
        super(context);
    }
}
