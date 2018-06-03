package com.sq.withyou.home;

import android.content.Context;
import android.util.Log;

import com.sq.lib_common.mvp.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by javakam on 2018/6/3.
 */
public class HomePresenter extends BasePresenter<HomeContract.HomeView> implements HomeContract.Presenter {
    HomeContract.HomeView view;

    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    public void getHomeArticleList() {
        view = getView();
        Disposable disposable = mRepository.getHomeArticleList(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Log.d(TAG, "onNext: " + response.toString());
                    if (response != null) {
                        view.showCollectList(response.getData());
                    }
                }, t -> Log.d(TAG, "onError: " + t.getMessage()));

    }
}
