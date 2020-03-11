package com.leaf.zapperdisplaydata.data.repository.database.persons;

import com.leaf.zapperdisplaydata.data.repository.database.persons.entity.PersonsEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface PersonsRepository {

    Flowable<List<PersonsEntity>> getPersonsEntity();

    Completable add(PersonsEntity... personsEntities);

    Completable update(PersonsEntity personsEntity);

    Completable delete(PersonsEntity personsEntity);

}
