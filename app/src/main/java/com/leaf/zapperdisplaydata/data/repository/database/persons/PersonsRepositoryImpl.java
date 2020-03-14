package com.leaf.zapperdisplaydata.data.repository.database.persons;

import com.leaf.zapperdisplaydata.data.remote.model.Person;
import com.leaf.zapperdisplaydata.data.repository.database.LocalDatabase;
import com.leaf.zapperdisplaydata.data.repository.database.persons.entity.PersonsEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class PersonsRepositoryImpl implements PersonsRepository {

    private LocalDatabase localDatabase;

    public PersonsRepositoryImpl(LocalDatabase localDatabase) {
        this.localDatabase = localDatabase;

    }

    @Override
    public Flowable<List<Person>> getPersons() {
        return Flowable.create(emitter -> {

            List<Person> personList = new ArrayList<>();

            List<PersonsEntity> personsEntities = localDatabase.personsDao().getPersonsEntities();

            for (PersonsEntity personsEntity : personsEntities) {
                Person person = new Person(personsEntity.getId(), personsEntity.getPersonsName());
                personList.add(person);
            }

            emitter.onNext(personList);

            emitter.onComplete();


        }, BackpressureStrategy.LATEST);
    }

    @Override
    public Completable add(Person person) {
        return Completable.create(emitter -> {
            try {

                PersonsEntity personsEntity = new PersonsEntity(person.getId(), person.getName());

                localDatabase.personsDao().add(personsEntity);
                emitter.onComplete();

            } catch (Throwable throwable) {
                emitter.onError(throwable);
            }
        });
    }

    @Override
    public Completable update(Person person) {
        return Completable.create(emitter -> {
            try {

                PersonsEntity personsEntity = new PersonsEntity(person.getId(), person.getName());

                localDatabase.personsDao().update(personsEntity);
                emitter.onComplete();

            } catch (Throwable throwable) {
                emitter.onError(throwable);
            }
        });
    }

    @Override
    public Completable delete(Person person) {
        return Completable.create(emitter -> {
            try {

                PersonsEntity personsEntity = new PersonsEntity(person.getId(), person.getName());

                localDatabase.personsDao().delete(personsEntity);
                emitter.onComplete();

            } catch (Throwable throwable) {
                emitter.onError(throwable);
            }
        });
    }
}
