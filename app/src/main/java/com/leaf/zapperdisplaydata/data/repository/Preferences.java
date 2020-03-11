package com.leaf.zapperdisplaydata.data.repository;

public enum Preferences {

    IS_PERSONS_CACHED("SharedPreferencePersonsCached");

    private String value;

    Preferences(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
