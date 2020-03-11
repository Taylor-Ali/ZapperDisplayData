package com.leaf.zapperdisplaydata.di.common;

import com.leaf.zapperdisplaydata.data.remote.PersonsUpdateImpl;
import com.leaf.zapperdisplaydata.data.remote.PersonsApi;
import com.leaf.zapperdisplaydata.data.remote.PersonsUpdates;
import com.leaf.zapperdisplaydata.data.remote.retrofit.RetrofitPersonsApi;
import com.leaf.zapperdisplaydata.data.remote.retrofit.RetrofitPersonsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {RetrofitServiceModule.class})
public class ApiModule {

    @Provides
    @Singleton
    PersonsApi providesPersonsApi(RetrofitPersonsService retrofitPersonsService) {
        return new RetrofitPersonsApi(retrofitPersonsService);
    }

    @Provides
    @Singleton
    PersonsUpdates providesPersonUpdates() {
        return PersonsUpdateImpl.getInstance();
    }
}
