package com.sq.data.wandroid.repository.server;


import android.util.Log;

import com.sq.data.wandroid.net.api.IWanApi;
import com.sq.domain.WanResponse;
import com.sq.domain.entity.HomeArticleListEntity;
import com.sq.domain.repository.INetRepository;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author javakam
 * @date 2018/5/23
 */
public class NetRepositoryImpl implements INetRepository {
    private IWanApi requestApi;
    private HashMap<String, Object> mRequestParam;

    public NetRepositoryImpl(IWanApi requestApi) {
        this.requestApi = requestApi;
        this.mRequestParam = new HashMap<>();
    }

    @Override
    public Flowable<WanResponse<HomeArticleListEntity>> getHomeArticleList(int num) {
        requestApi.
                getHomeArticleList(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WanResponse<HomeArticleListEntity>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe: " + s);
                    }

                    @Override
                    public void onNext(WanResponse<HomeArticleListEntity> homeArticleListEntityWanResponse) {
                        Log.d(TAG, "onNext: " + homeArticleListEntityWanResponse.toString());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d(TAG, "onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
        return null;
         /* return isLocal ? mLocalRepository.getHomeArticleList(loginId, mode)
                .flatMap(list -> {
                    if (list == null || list.size() == 0) {
                        return Flowable.error(new Throwable("未获取到离线数据"));
                    }
                    return Flowable.just(list);
                }) : mServerRepository.getHomeArticleList(loginId, mode);*/
    }
//    @Override
//    public Flowable<UserEntity> login(String userName, String password) {
//        mRequestParam.clear();
//        mRequestParam.put("loginId", userName.toUpperCase());
//        mRequestParam.put("password", password.toUpperCase());
//        return mRequestApi.login(JsonUtil.map2Json(mRequestParam)).compose(TransformerHelper.handleResponse());
//    }

}
