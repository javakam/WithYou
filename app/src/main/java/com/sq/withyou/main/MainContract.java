package com.sq.withyou.main;

import com.sq.lib_common.mvp.BaseView;
import com.sq.lib_common.mvp.IPresenter;

/**
 * @author javakam
 * @date 2018/6/3
 */
public class MainContract {
    interface View extends BaseView {
        void showExitDialog();
    }

    interface Presenter extends IPresenter<View> {
    }
}
