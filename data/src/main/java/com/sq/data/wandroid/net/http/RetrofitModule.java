package com.sq.data.wandroid.net.http;

import android.util.Log;

import com.sq.data.wandroid.net.api.IWanApi;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author javakam
 */
public class RetrofitModule {
    private final static int DEFUALT_TIME_OUT = 60;
    private static IWanApi sRequest;

    public static IWanApi getRequestApi(String baseUrl) {
        //注意这里为了增加baseUrl能够修改，不在将WanApi设置为单例
        if (sRequest == null) {
            //拦截器
            Interceptor interceptor = chain -> {
                Request oldRequest = chain.request();
                // 添加新的参数
                HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                        .newBuilder()
                        .scheme(oldRequest.url().scheme())
                        .host(oldRequest.url().host());
                //添加MAC地址作为本机的唯一标识
//                        .addQueryParameter("macAddress", Global.MAC_ADDRESS)
//                        .addQueryParameter("userId", Global.USER_ID)
//                        .addQueryParameter("dbSource", Global.DBSOURCE);

                // 新的请求
                Request newRequest = oldRequest.newBuilder()
                        .method(oldRequest.method(), oldRequest.body())
                        //请求头增加字段表明来源：1：iOS, 2:Android, 3:web
//                        .header("registeredChannels", "2")
                        .url(authorizedUrlBuilder.build())
                        .build();
                return chain.proceed(newRequest);
            };
            //打印拦截器
            HttpLoggingInterceptor logging =
                    new HttpLoggingInterceptor(message -> Log.d("123", message));
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)//添加拦截器
                    .addInterceptor(logging)//添加打印拦截器
                    .connectTimeout(DEFUALT_TIME_OUT, TimeUnit.SECONDS)//设置请求超时时间
                    .readTimeout(DEFUALT_TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFUALT_TIME_OUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build();
            sRequest = retrofit.create(IWanApi.class);
        }
        return sRequest;
    }

    public static void setRequestApi(IWanApi requestApi) {
        sRequest = requestApi;
    }
}

