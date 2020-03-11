package com.leaf.zapperdisplaydata.data.repository.database.persons.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonsDao {

    @Query("Select * from  PersonsEntity ORDER BY ID")
    List<PersonsEntity> getLocations();

    @Insert
    void addLocation(PersonsEntity personsEntity);

    @Update
    void updateLocation(PersonsEntity locationsEntity);

    @Delete
    void delete(PersonsEntity personsEntity);

}
