package com.sq.withyou.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sq.data.wandroid.net.http.RetrofitModule;
import com.sq.data.wandroid.repository.Repository;
import com.sq.data.wandroid.repository.server.NetRepositoryImpl;
import com.sq.lib_common.base.BaseApplication;
import com.sq.lib_common.base.BaseIndicateFragment;
import com.sq.withyou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link BaseIndicateFragment} subclass.
 */
public class HomeFragment extends BaseIndicateFragment {
    @BindView(R.id.rv_home)
    protected RecyclerView mRecyclerView;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btGetWanHome)
    public void getWanHome(View view) {
        Repository mRepository = new Repository(new NetRepositoryImpl
                (RetrofitModule.getRequestApi(BaseApplication.baseUrl)));
        mRepository.getHomeArticleList(10);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }
}
