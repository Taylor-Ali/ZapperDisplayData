package com.leaf.zapperdisplaydata.data.repository.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.leaf.zapperdisplaydata.data.repository.database.persons.entity.PersonsDao;
import com.leaf.zapperdisplaydata.data.repository.database.persons.entity.PersonsEntity;

import dagger.Module;

@Module
@Database(entities = {PersonsEntity.class}, exportSchema = false, version = 1)
public abstract class LocalDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "zapper_display_data_database";
    private static LocalDatabase instance;

    public static synchronized LocalDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract PersonsDao personsDao();
}
