package com.leaf.zapperdisplaydata.di.common;

import android.content.SharedPreferences;

import com.leaf.zapperdisplaydata.data.repository.sharedpreference.DatabasePreferences;
import com.leaf.zapperdisplaydata.data.repository.sharedpreference.DatabasePreferencesImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    @Singleton
    DatabasePreferences providesDatabasePreference(SharedPreferences sharedPreferences) {
        return new DatabasePreferencesImpl(sharedPreferences);
    }
}
