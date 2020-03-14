package com.leaf.zapperdisplaydata.data.service;

import com.leaf.zapperdisplaydata.data.remote.model.Persons;

import io.reactivex.Flowable;

public interface PersonsUpdates {
    Flowable<Persons> onPersonsUpdate();
}
