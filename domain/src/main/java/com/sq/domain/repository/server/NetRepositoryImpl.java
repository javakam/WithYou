package com.sq.domain.repository.server;

import com.sq.data.wandroid.WanResponse;
import com.sq.data.wandroid.entity.HomeArticleListEntity;
import com.sq.data.wandroid.repository.INetRepository;
import com.sq.domain.net.api.IWanApi;

import java.util.HashMap;

import io.reactivex.Flowable;

/**
 * Created by javakam on 2018/5/23.
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
        return requestApi.getHomeArticleList(num);
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
