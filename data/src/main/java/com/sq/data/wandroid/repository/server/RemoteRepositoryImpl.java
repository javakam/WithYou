package com.sq.data.wandroid.repository.server;

import com.sq.data.wandroid.net.api.IWanApi;
import com.sq.data.wandroid.net.http.RetrofitModule;
import com.sq.domain.WanResponse;
import com.sq.domain.entity.HomeArticleListEntity;
import com.sq.domain.repository.IRemoteRepository;

import java.util.HashMap;

import io.reactivex.Flowable;

/**
 * @author javakam
 * @date 2018/5/23
 */
public class RemoteRepositoryImpl implements IRemoteRepository {
    private IWanApi requestApi;
    private HashMap<String, Object> mRequestParam;

    public RemoteRepositoryImpl(String baseUrl) {
        this.requestApi = RetrofitModule.getRequestApi(baseUrl);
//        this.mRequestParam = new HashMap<>();
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
