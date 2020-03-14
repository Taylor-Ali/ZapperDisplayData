package com.leaf.zapperdisplaydata.data.repository.database.persons;

import com.leaf.zapperdisplaydata.data.remote.model.Person;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface PersonsRepository {

    Flowable<List<Person>> getPersons();

    Completable add(Person person);

    Completable update(Person person);

    Completable delete(Person person);

}
