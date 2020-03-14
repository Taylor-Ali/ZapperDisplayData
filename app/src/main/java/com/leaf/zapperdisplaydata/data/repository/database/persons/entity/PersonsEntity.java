package com.leaf.zapperdisplaydata.data.repository.database.persons.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity(tableName = "PersonsEntity")
public class PersonsEntity {

    @PrimaryKey(autoGenerate = false)
    private Integer id;

    @ColumnInfo(name = "person_name")
    private String personsName;


    public PersonsEntity(Integer id, String personsName) {
        this.id = id;
        this.personsName = personsName;
    }

    @Ignore
    public PersonsEntity() {
    }

    @Ignore
    public PersonsEntity(String personsName) {
        this.personsName = personsName;
    }
}
