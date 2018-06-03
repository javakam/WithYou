package com.sq.withyou.home;

import com.sq.domain.entity.HomeArticleListEntity;
import com.sq.lib_common.mvp.BaseView;
import com.sq.lib_common.mvp.IPresenter;

/**
 * Created by javakam on 2018/6/2.
 */
public class HomeContract {
    interface HomeView extends BaseView {
        /**
         * 首页列表
         *
         * @param homeArticleEntity
         */
        void showCollectList(HomeArticleListEntity homeArticleEntity);
    }

    interface Presenter extends IPresenter<HomeView> {
        void getHomeArticleList();
    }
}
