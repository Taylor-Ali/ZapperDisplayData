package com.leaf.zapperdisplaydata.data.remote.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class PersonDetails {

    @SerializedName("person")
    private String name;

    @SerializedName("team")
    private String team;

    public PersonDetails(String name, String team) {
        this.name = name;
        this.team = team;
    }
}
