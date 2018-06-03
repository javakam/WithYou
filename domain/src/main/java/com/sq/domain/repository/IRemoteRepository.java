package com.sq.domain.repository;

import com.sq.domain.WanResponse;
import com.sq.domain.entity.HomeArticleListEntity;

import io.reactivex.Flowable;
import retrofit2.http.Path;

/**
 * Created by javakam on 2018/5/23.
 */
public interface IRemoteRepository extends IRepository{

    Flowable<WanResponse<HomeArticleListEntity>> getHomeArticleList(@Path("num") int num);
}
