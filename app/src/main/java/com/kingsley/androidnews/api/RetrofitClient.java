package com.kingsley.androidnews.api;

import com.kingsley.androidnews.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * class name : RetrofitClient
 * created date : on 2017/12/20 22:08
 *
 * @author Kingsley
 * @version 1.0
 */

public class RetrofitClient {
    private static Retrofit.Builder mRetrofitClient;

    private RetrofitClient() {
    }

    private static Retrofit.Builder getRetroClient() {
        if (mRetrofitClient == null) {
            synchronized (RetrofitClient.class) {
                if (mRetrofitClient == null) {
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    httpLoggingInterceptor.setLevel(BuildConfig.LOG_DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
                    OkHttpClient okHttpClient = new OkHttpClient
                            .Builder()
                            .addInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request request = chain.request();
                                    Request cookie = request.newBuilder()
                                            .build();
                                    return chain.proceed(cookie);
                                }
                            })
                            .addNetworkInterceptor(httpLoggingInterceptor)
                            .retryOnConnectionFailure(true)
                            .build();
                    mRetrofitClient = new Retrofit
                            .Builder()
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

                }
            }
        }
        return mRetrofitClient;
    }

    /**
     * 获取WanAndroid API
     */
    public static WanAndroidApi getWanAndroidApiService() {
        return getRetroClient()
                .baseUrl(WanAndroidApi.BASE_URL)
                .build()
                .create(WanAndroidApi.class);
    }

    /**
     * 获取GankIo Api
     */

    public static GankIoApi getGankIoApiService(){
        return getRetroClient()
                .baseUrl(GankIoApi.HOST)
                .build()
                .create(GankIoApi.class);
    }
}
