package com.leaf.zapperdisplaydata.di.common;

import com.leaf.zapperdisplaydata.data.remote.retrofit.RetrofitPersonsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RetrofitServiceModule {

    @Provides
    @Singleton
    RetrofitPersonsService providesRetrofitPersonsService(Retrofit retrofit) {
        return retrofit.create(RetrofitPersonsService.class);
    }
}
