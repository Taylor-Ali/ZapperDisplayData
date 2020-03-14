package com.leaf.zapperdisplaydata.data;

import com.leaf.zapperdisplaydata.data.remote.model.Person;
import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

public interface PersonsDataLayer {

    void init();

    void destroy();

    Flowable<List<Person>> getPersons();

    Flowable<PersonDetails> getPersonDetails(Integer id);

    Flowable<String> onPersonsUpdate();

    boolean checkIfDatabaseIsLoadedAndLoadItWithData();

    void updateDatabaseSharedPreferencesFlag();

    void addDataLayerSubscription(Disposable disposable);

    void clearDataLayerSubscription();


}

