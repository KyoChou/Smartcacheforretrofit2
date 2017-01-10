
package com.lizubing.smartcacheforretrofit2.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lizubing.smartcache.BasicCaching;
import com.lizubing.smartcache.SmartCallFactory;
import com.lizubing.smartcacheforretrofit2.MyApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 无缓存get post请求
 * 支持Rxjava Observable
 */
public class MainFactory {
    public static final String HOST = "http://192.168.1.99:8000/";

    private static MeoHttp mGuDong;

    protected static final Object monitor = new Object();

    public static MeoHttp getInstance(){
        synchronized (monitor){
            if(mGuDong==null){
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                        .create();
                SmartCallFactory smartFactory = new SmartCallFactory(BasicCaching.fromCtx(MyApplication.getContext()));

                //设置OKHttpClient
                OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                        .connectTimeout(2, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS);
                //build OKHttpClient
                OkHttpClient okHttpClient = httpClientBuilder.build();
                Retrofit client = new Retrofit.Builder()
                        .baseUrl(HOST)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(smartFactory)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                mGuDong = client.create(MeoHttp.class);
            }
            return mGuDong;
        }
    }
}
