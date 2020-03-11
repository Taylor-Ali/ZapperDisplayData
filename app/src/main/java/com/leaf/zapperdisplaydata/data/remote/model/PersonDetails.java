package com.leaf.zapperdisplaydata.data.remote.model;

import com.google.gson.annotations.SerializedName;

public class PersonDetails {

    @SerializedName("name")
    private Integer id;

    @SerializedName("name")
    private String name;

    public PersonDetails(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
