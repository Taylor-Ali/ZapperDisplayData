package com.leaf.zapperdisplaydata.di.common;

import android.content.Context;

import com.leaf.zapperdisplaydata.data.repository.database.LocalDatabase;
import com.leaf.zapperdisplaydata.data.repository.database.persons.PersonsRepository;
import com.leaf.zapperdisplaydata.data.repository.database.persons.PersonsRepositoryImpl;
import com.leaf.zapperdisplaydata.data.repository.sharedpreference.DatabasePreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryServiceModule {


    @Provides
    @Singleton
    LocalDatabase providesLocalDatabase(Context context) {
        return LocalDatabase.getInstance(context);
    }

    @Provides
    @Singleton
    PersonsRepository providesPersonsRepository(LocalDatabase localDatabase) {
        return new PersonsRepositoryImpl(localDatabase);
    }
}
