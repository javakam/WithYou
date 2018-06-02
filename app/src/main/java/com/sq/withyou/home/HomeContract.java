package com.sq.withyou.home;

import android.content.Context;

import com.sq.lib_common.mvp.BasePresenter;
import com.sq.lib_common.mvp.BaseView;

/**
 * Created by javakam on 2018/6/2.
 */
public class HomeContract {

    interface HomeView extends BaseView {
    }

    class HomePresenter extends BasePresenter {
        public HomePresenter(Context context) {
            super(context);
        }
    }
}
