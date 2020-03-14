package com.leaf.zapperdisplaydata.ui.persons.model;

import com.leaf.zapperdisplaydata.data.PersonsDataLayer;
import com.leaf.zapperdisplaydata.data.remote.model.Person;
import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;
import com.leaf.zapperdisplaydata.ui.persons.PersonsContract;

import java.util.List;

import io.reactivex.Flowable;

public class PersonsModel implements PersonsContract.Model {
    private PersonsDataLayer personsDataLayer;

    public PersonsModel(PersonsDataLayer personsDataLayer) {
        this.personsDataLayer = personsDataLayer;
    }

    @Override
    public Flowable<List<Person>> getPersons() {
        return personsDataLayer.getPersons();
    }

    @Override
    public Flowable<PersonDetails> getPersonDetail(Integer id) {
        return personsDataLayer.getPersonDetails(id);
    }
}
