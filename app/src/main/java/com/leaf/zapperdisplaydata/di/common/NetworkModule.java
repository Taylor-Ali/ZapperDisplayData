package com.leaf.zapperdisplaydata.di.common;


import android.content.Context;

import com.leaf.zapperdisplaydata.R;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    @Named("HttpConnectTimeout")
    Integer providesHttpConnectTimeout(Context applicationContext) {
        return applicationContext.getResources()
                .getInteger(R.integer.http_connect_timeout_in_seconds);
    }

    @Provides
    @Singleton
    @Named("HttpReadTimeout")
    Integer providesHttpReadTimeout(Context applicationContext) {
        return applicationContext.getResources()
                .getInteger(R.integer.http_read_timeout_in_seconds);
    }

    @Provides
    @Singleton
    @Named("HttpWriteTimeout")
    Integer providesHttpWriteTimeout(Context applicationContext) {
        return applicationContext.getResources()
                .getInteger(R.integer.http_write_timeout_in_seconds);
    }

    @Provides
    @Singleton
    Interceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Provides
    @Singleton
    OkHttpClient providesHttpClient(Context applicationContext,
                                    Interceptor httpLoggingInterceptor,
                                    @Named("HttpConnectTimeout") Integer connectTimeout,
                                    @Named("HttpReadTimeout") Integer readTimeout,
                                    @Named("HttpWriteTimeout") Integer writeTimeout) {

        OkHttpClient.Builder builder = null;

        builder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);


        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("http://demo9790103.mockable.io/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}