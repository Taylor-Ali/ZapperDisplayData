package com.leaf.zapperdisplaydata.data.remote.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Person {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
