package com.leaf.zapperdisplaydata.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Persons {

    @SerializedName("msg")
    private String message;

    @SerializedName("persons")
    private List<Person> personList;

    public Persons() {
    }
}
