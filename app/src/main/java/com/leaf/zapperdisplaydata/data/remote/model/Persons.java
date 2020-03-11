package com.leaf.zapperdisplaydata.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Persons {

    @SerializedName("msg")
    private String message;

    @SerializedName("persons")
    private List<Person> personList;

}
