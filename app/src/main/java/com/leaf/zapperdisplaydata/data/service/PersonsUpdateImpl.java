package com.leaf.zapperdisplaydata.data.service;

import com.leaf.zapperdisplaydata.data.remote.model.Persons;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class PersonsUpdateImpl implements PersonsUpdates {
    public static PublishSubject<Persons> personsUpdateStringPublishSubject = PublishSubject.create();

    static PersonsUpdateImpl instance;

    private PersonsUpdateImpl() {
    }

    public static PersonsUpdateImpl getInstance() {
        if (instance == null) {
            instance = new PersonsUpdateImpl();
        }
        return instance;
    }

    @Override
    public Flowable<Persons> onPersonsUpdate() {
        return personsUpdateStringPublishSubject
                .toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
