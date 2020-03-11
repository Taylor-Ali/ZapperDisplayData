package com.leaf.zapperdisplaydata.data.remote.retrofit;

import com.leaf.zapperdisplaydata.data.remote.PersonsApi;
import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;
import com.leaf.zapperdisplaydata.data.remote.model.Persons;

import io.reactivex.Flowable;

public class RetrofitPersonsApi implements PersonsApi {

    private RetrofitPersonsService retrofitPersonsService;

    public RetrofitPersonsApi(RetrofitPersonsService retrofitWeatherService) {
        this.retrofitPersonsService = retrofitWeatherService;
    }

    @Override
    public Flowable<Persons> getPersonsList() {
        return retrofitPersonsService.getPersons();
    }

    @Override
    public Flowable<PersonDetails> getPersonDetails(Integer id) {
        return retrofitPersonsService.getPersonDetails(id);
    }
}
