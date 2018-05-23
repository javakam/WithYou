package com.sq.data.wandroid.repository;

import com.sq.data.wandroid.WanResponse;
import com.sq.data.wandroid.entity.HomeArticleListEntity;

import io.reactivex.Flowable;
import retrofit2.http.Path;

/**
 * Created by javakam on 2018/5/23.
 */
public interface INetRepository extends IRepository{
    Flowable<WanResponse<HomeArticleListEntity>> getHomeArticleList(@Path("num") int num);
}
