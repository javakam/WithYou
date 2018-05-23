package com.sq.domain.repository;

import android.util.Log;

import com.sq.data.wandroid.WanResponse;
import com.sq.data.wandroid.entity.HomeArticleListEntity;
import com.sq.data.wandroid.repository.ILocalRepository;
import com.sq.data.wandroid.repository.INetRepository;

import io.reactivex.Flowable;

/**
 * @author javakam
 * @date 2018-5-23
 */
public final class Repository implements ILocalRepository, INetRepository {
    private ILocalRepository mLocalRepository;
    private INetRepository mServerRepository;
    private boolean isLocal;

    private Repository() {
    }

    public Repository(INetRepository serverRepository) {
        this.mServerRepository = serverRepository;
    }

    public Repository(INetRepository serverRepository, ILocalRepository localRepository) {
        this.mLocalRepository = localRepository;
        this.mServerRepository = serverRepository;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
        Log.e("123", "isLocal = " + isLocal);
    }

    @Override
    public Flowable<WanResponse<HomeArticleListEntity>> getHomeArticleList(int num) {
        return mServerRepository.getHomeArticleList(num);
    }


//    @Override
//    public Flowable<UserEntity> login(String userName, String password) {
//        return isLocal ? mLocalRepository.login(userName, password) : mServerRepository.login(userName, password);
//    }


}
