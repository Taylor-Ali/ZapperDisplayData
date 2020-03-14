package com.leaf.zapperdisplaydata.di.common;


import com.leaf.zapperdisplaydata.data.PersonDataLayerImpl;
import com.leaf.zapperdisplaydata.data.PersonsDataLayer;
import com.leaf.zapperdisplaydata.data.remote.PersonsApi;
import com.leaf.zapperdisplaydata.data.repository.database.persons.PersonsRepository;
import com.leaf.zapperdisplaydata.data.repository.sharedpreference.DatabasePreferences;
import com.leaf.zapperdisplaydata.data.service.PersonsUpdates;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataLayerModule {
    @Provides
    @Singleton
    PersonsDataLayer providesPersonsDataLayer(PersonsRepository personsRepository, DatabasePreferences
            databasePreferences, PersonsApi personsApi, PersonsUpdates personsUpdates) {
        return new PersonDataLayerImpl(personsRepository, databasePreferences, personsApi, personsUpdates);
    }

}
