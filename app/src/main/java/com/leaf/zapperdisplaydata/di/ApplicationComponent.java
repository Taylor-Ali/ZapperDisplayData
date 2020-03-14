package com.leaf.zapperdisplaydata.di;


import com.leaf.zapperdisplaydata.data.PersonsDataLayer;
import com.leaf.zapperdisplaydata.di.common.AndroidModule;
import com.leaf.zapperdisplaydata.di.common.ApiModule;
import com.leaf.zapperdisplaydata.di.common.DataLayerModule;
import com.leaf.zapperdisplaydata.di.common.NetworkModule;
import com.leaf.zapperdisplaydata.di.common.RepositoryServiceModule;
import com.leaf.zapperdisplaydata.di.common.StorageModule;
import com.leaf.zapperdisplaydata.di.main.persondetails.PersonDetailsComponent;
import com.leaf.zapperdisplaydata.di.main.persons.PersonsComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton

@Component(modules = {AndroidModule.class, ApiModule.class, NetworkModule.class,
        RepositoryServiceModule.class, StorageModule.class, DataLayerModule.class})

public interface ApplicationComponent {
    PersonsComponent personsComponent();

    PersonDetailsComponent personDetailsComponent();
}
