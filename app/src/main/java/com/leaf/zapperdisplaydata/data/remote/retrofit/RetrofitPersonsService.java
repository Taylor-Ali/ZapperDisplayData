package com.leaf.zapperdisplaydata.data.remote.retrofit;

import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;
import com.leaf.zapperdisplaydata.data.remote.model.Persons;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitPersonsService {

    @GET("persons")
    Flowable<Persons> getPersons();

    @GET("person/{id}")
    Flowable<PersonDetails> getPersonDetails(@Path("id") int id);
}
