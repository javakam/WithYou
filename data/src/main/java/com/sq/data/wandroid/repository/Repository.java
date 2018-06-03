package com.sq.data.wandroid.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sq.data.wandroid.repository.server.RemoteRepositoryImpl;
import com.sq.domain.WanResponse;
import com.sq.domain.entity.HomeArticleListEntity;
import com.sq.domain.repository.ILocalRepository;
import com.sq.domain.repository.IRemoteRepository;

import io.reactivex.Flowable;


/**
 * @author javakam
 * @date 2018-5-23
 */
public final class Repository implements ILocalRepository, IRemoteRepository {
    private static Repository INSTANCE ;
    //    private ILocalRepository mLocalRepository;
    @NonNull
    private IRemoteRepository mRemoteRepository;
    private boolean isLocal;

    private Repository() {
    }

    private Repository(@NonNull String baseUrl) {
//        this.mLocalRepository = new LocalRepositoryImpl();
        this.mRemoteRepository = new RemoteRepositoryImpl(baseUrl);
    }

    public static Repository getInstance(@NonNull String baseUrl) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(baseUrl);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
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
        return mRemoteRepository.getHomeArticleList(num);
    }

//    @Override
//    public Flowable<UserEntity> login(String userName, String password) {
//        return isLocal ? mLocalRepository.login(userName, password) : mRemoteRepository.login(userName, password);
//    }


}
