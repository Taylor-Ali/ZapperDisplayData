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
    List<PersonsEntity> getPersonsEntities();

    @Insert
    void add(PersonsEntity personsEntity);

    @Update
    void update(PersonsEntity personsEntity);

    @Delete
    void delete(PersonsEntity personsEntity);

}
