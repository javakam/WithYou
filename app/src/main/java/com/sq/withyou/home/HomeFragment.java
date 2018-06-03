package com.sq.withyou.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.sq.domain.entity.HomeArticleEntity;
import com.sq.domain.entity.HomeArticleListEntity;
import com.sq.lib_common.base.BaseIndicateFragment;
import com.sq.lib_common.mvp.MvpFragment;
import com.sq.lib_common.utils.ACache;
import com.sq.lib_common.utils.GsonUtils;
import com.sq.lib_common.utils.NetUtils;
import com.sq.withyou.Constant;
import com.sq.withyou.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link BaseIndicateFragment} subclass.
 */
public class HomeFragment extends MvpFragment<HomePresenter> implements HomeContract.HomeView {
    @BindView(R.id.rv_home)
    protected RecyclerView mRecyclerView;
    private HomeAdapter adapter;
    private List<HomeArticleEntity> mArticles;
    private ACache aCache;

    @Override
    protected int getContentId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter(mActivity);
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {
        aCache = ACache.get(mActivity);
    }

    @Override
    protected void initView() {
        mArticles = new ArrayList<>();
        if (!NetUtils.isConnected()) {
            mArticles = GsonUtils.fromJson(aCache.getAsString(Constant.CACHE_HOME_ARTICLE));
        } else {
            mPresenter.getHomeArticleList();
        }
        adapter = new HomeAdapter(R.layout.item_home_list, mArticles);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

    }

    @Override
    public void showCollectList(HomeArticleListEntity homeArticleEntity) {
        if (homeArticleEntity == null) {
            return;
        }
        mArticles = homeArticleEntity.getDatas();
        aCache.put(Constant.CACHE_HOME_ARTICLE, new Gson().toJson(mArticles));
        adapter.setNewData(mArticles);
    }
}
