package com.leaf.zapperdisplaydata.data.remote;

import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;
import com.leaf.zapperdisplaydata.data.remote.model.Persons;

import io.reactivex.Flowable;

public interface PersonsApi {

    Flowable<Persons> getPersonsList();

    Flowable<PersonDetails> getPersonDetails(Integer id);
}
