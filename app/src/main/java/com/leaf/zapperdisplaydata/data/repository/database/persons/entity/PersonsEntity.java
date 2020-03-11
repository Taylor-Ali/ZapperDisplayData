package com.leaf.zapperdisplaydata.data.repository.database.persons.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity(tableName = "PersonsEntity")
public class PersonsEntity {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "personsResponse")
    private String personsResponse;

    //TODO: Replace with a TypeConverter  https://developer.android.com/training/data-storage/room/referencing-data


    public PersonsEntity(Long id, String personsResponse) {
        this.id = id;
        this.personsResponse = personsResponse;
    }

    @Ignore
    public PersonsEntity() {
    }

    @Ignore
    public PersonsEntity(String personsResponse) {
        this.personsResponse = personsResponse;
    }
}
