package com.leaf.zapperdisplaydata.ui.personsdetails.model;

import com.leaf.zapperdisplaydata.data.remote.PersonsApi;
import com.leaf.zapperdisplaydata.data.remote.model.Person;
import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;
import com.leaf.zapperdisplaydata.ui.personsdetails.PersonDetailsContract;

import io.reactivex.Flowable;

public class PersonDetailsModel implements PersonDetailsContract.Model {
    private PersonsApi personsApi;

    public PersonDetailsModel(PersonsApi personsApi) {
        this.personsApi = personsApi;
    }

    @Override
    public Flowable<PersonDetails> getPersonDetail(Integer id) {
        return personsApi.getPersonDetails(id);
    }
}
