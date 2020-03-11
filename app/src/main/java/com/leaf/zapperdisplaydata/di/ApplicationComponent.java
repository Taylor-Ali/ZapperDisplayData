package com.leaf.zapperdisplaydata.di;


import com.leaf.zapperdisplaydata.di.common.AndroidModule;
import com.leaf.zapperdisplaydata.di.common.ApiModule;
import com.leaf.zapperdisplaydata.di.common.NetworkModule;
import com.leaf.zapperdisplaydata.di.common.RepositoryServiceModule;
import com.leaf.zapperdisplaydata.di.common.StorageModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton

@Component(modules = {AndroidModule.class, ApiModule.class, NetworkModule.class,
        RepositoryServiceModule.class, StorageModule.class})

public interface ApplicationComponent {
}
