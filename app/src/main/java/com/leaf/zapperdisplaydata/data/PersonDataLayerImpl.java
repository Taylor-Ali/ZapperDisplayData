package com.leaf.zapperdisplaydata.data;

import android.util.Log;

import com.leaf.zapperdisplaydata.data.remote.PersonsApi;
import com.leaf.zapperdisplaydata.data.remote.model.Person;
import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;
import com.leaf.zapperdisplaydata.data.remote.model.Persons;
import com.leaf.zapperdisplaydata.data.repository.database.persons.PersonsRepository;
import com.leaf.zapperdisplaydata.data.repository.sharedpreference.DatabasePreferences;
import com.leaf.zapperdisplaydata.data.service.PersonsUpdates;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PersonDataLayerImpl implements PersonsDataLayer {

    private PersonsRepository personsRepository;
    private DatabasePreferences databasePreferences;
    private PersonsApi personsApi;
    private PersonsUpdates personsUpdates;

    private CompositeDisposable subscriptions = new CompositeDisposable();


    public PersonDataLayerImpl(PersonsRepository personsRepository, DatabasePreferences
            databasePreferences, PersonsApi personsApi, PersonsUpdates personsUpdates) {

        this.personsRepository = personsRepository;
        this.databasePreferences = databasePreferences;
        this.personsApi = personsApi;
        this.personsUpdates = personsUpdates;

        init();
    }

    @Override
    public void init() {

        subscribeToPersonsFromRemoteUpdates();
    }

    @Override
    public void destroy() {
        clearDataLayerSubscription();
    }

    @Override
    public Flowable<List<Person>> getPersons() {
        return Flowable.create(emitter -> {

            List<Person> personList = new ArrayList<>();


            if (checkIfDatabaseIsLoadedAndLoadItWithData()) {
                Log.v(PersonDataLayerImpl.class.getSimpleName(), "checkIfDatabaseIsLoadedAndLoadItWithData");
                personList = getPersonsFromRepository();
            } else {
                personList = getPersonsFromRemote();
            }

            emitter.onNext(personList);

        }, BackpressureStrategy.LATEST);
    }

    @Override
    public Flowable<PersonDetails> getPersonDetails(Integer id) {
        return personsApi.getPersonDetails(id);
    }

    @Override
    public Flowable<String> onPersonsUpdate() {
        return null;
    }

    @Override
    public boolean checkIfDatabaseIsLoadedAndLoadItWithData() {
        return databasePreferences.retrieveDatabaseFlag().blockingFirst();
    }

    @Override
    public void updateDatabaseSharedPreferencesFlag() {
        addDataLayerSubscription(
                databasePreferences.storeDatabaseFlag(Boolean.TRUE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {
                        }, Throwable::printStackTrace));
    }

    @Override
    public void addDataLayerSubscription(Disposable disposable) {
        subscriptions.add(disposable);
    }

    @Override
    public void clearDataLayerSubscription() {
        subscriptions.clear();
    }

    private List<Person> getPersonsFromRemote() {

        Persons persons = personsApi.getPersonsList().blockingFirst();

        List<Person> personList = persons.getPersonList();

        for (Person person : personList) {
            addPersonsToDatabase(person);
        }

        return personList;

    }

    private List<Person> getPersonsFromRepository() {

        return personsRepository.getPersons().blockingFirst();
    }


    private void subscribeToPersonsFromRemoteUpdates() {
        addDataLayerSubscription(
                personsUpdates.onPersonsUpdate()
                        .subscribe(persons -> {
                            List<Person> personList = persons.getPersonList();

                            for (Person person : personList) {
                                updatePersonsDatabase(person);
                            }
                        }, Throwable::printStackTrace));

    }


    private void addPersonsToDatabase(Person person) {
        addDataLayerSubscription(
                personsRepository.add(person)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::updateDatabaseSharedPreferencesFlag, Throwable::printStackTrace));
    }

    private void updatePersonsDatabase(Person person) {
        addDataLayerSubscription(
                personsRepository.update(person)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::updateDatabaseSharedPreferencesFlag, Throwable::printStackTrace));
    }

}
