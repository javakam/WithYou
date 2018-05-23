package com.sq.data.wandroid.net.api;


import com.sq.domain.WanResponse;
import com.sq.domain.entity.HomeArticleListEntity;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * WanAndroid API
 * <p>
 * http://www.wanandroid.com/blog/show/2
 *
 * @author javakam
 * @date 2018/2/12
 */
public interface IWanApi {

    String HOST = "http://www.wanandroid.com/";

    /**
     * 首页文章列表
     * <p>
     * http://www.wanandroid.com/article/list/1/json
     *
     * @param num 页数
     * @return 首页文章列表数据
     */
    @GET("article/list/{num}/json")
    Flowable<WanResponse<HomeArticleListEntity>> getHomeArticleList(@Path("num") int num);

    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     *
     * @return 常用网站数据
     */
//    @GET("friend/json")
//    Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites();


    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     *
     * @return 知识体系数据
     */
//    @GET("tree/json")
//    Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData();

//    /**
//     * 知识体系下的文章
//     * http://www.wanandroid.com/article/list/0?cid=60
//     *
//     * @param page page num
//     * @param cid second page id
//     * @return 知识体系feed文章数据
//     */
//    @GET("article/list/{page}/json")
//    Observable<BaseResponse<FeedArticleListData>> getKnowledgeHierarchyDetailData(@Path("page") int page, @Query("cid") int cid);

    /**
     * 导航
     * http://www.wanandroid.com/navi/json
     *
     * @return 导航数据
     */
//    @GET("navi/json")
//    Observable<BaseResponse<List<NavigationListData>>> getNavigationListData();

    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     *
     * @return 项目分类数据
     */
//    @GET("project/tree/json")
//    Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData();

    /**
     * 项目类别数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page page num
     * @param cid second page id
     * @return 项目类别数据
     */
//    @GET("project/list/{page}/json")
//    Observable<BaseResponse<ProjectListData>> getProjectListData(@Path("page") int page, @Query("cid") int cid);

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param username user name
     * @param password password
     * @return 登陆数据
     */
//    @POST("user/login")
//    @FormUrlEncoded
//    Observable<BaseResponse<LoginData>> getLoginData(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     *
     * @param username user name
     * @param password password
     * @param repassword re password
     * @return 注册数据
     */
//    @POST("user/register")
//    @FormUrlEncoded
//    Observable<BaseResponse<LoginData>> getRegisterData(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


}
