package com.leaf.zapperdisplaydata.utils;

import com.google.gson.Gson;
import com.leaf.zapperdisplaydata.data.remote.model.Persons;

public class JsonUtil {

    public static Persons convertFromJSONPersonResponse(String jsonObject) {
        Persons persons = new Gson().fromJson(jsonObject, Persons.class);
        return persons;
    }
}
