package com.leaf.zapperdisplaydata.data.repository.database.persons;

import com.leaf.zapperdisplaydata.data.repository.database.LocalDatabase;
import com.leaf.zapperdisplaydata.data.repository.database.persons.entity.PersonsEntity;
import com.leaf.zapperdisplaydata.data.repository.sharedpreference.DatabasePreferences;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;

public class PersonsRepositoryImpl implements PersonsRepository {

    private CompositeDisposable subscriptions = new CompositeDisposable();
    private LocalDatabase localDatabase;
    private DatabasePreferences databasePreferences;

    public PersonsRepositoryImpl(LocalDatabase localDatabase, DatabasePreferences databasePreferences) {
        this.localDatabase = localDatabase;
        this.databasePreferences = databasePreferences;
    }

    @Override
    public Flowable<List<PersonsEntity>> getPersonsEntity() {
        return null;
    }

    @Override
    public Completable add(PersonsEntity... personsEntities) {
        return null;
    }

    @Override
    public Completable update(PersonsEntity personsEntity) {
        return null;
    }

    @Override
    public Completable delete(PersonsEntity personsEntity) {
        return null;
    }
}
