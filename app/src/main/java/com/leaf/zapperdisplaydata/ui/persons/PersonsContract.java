package com.leaf.zapperdisplaydata.ui.persons;

import androidx.lifecycle.LiveData;

import com.leaf.zapperdisplaydata.data.remote.model.Person;
import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;

import java.util.List;

import io.reactivex.Flowable;

public interface PersonsContract {

    interface View {
        void updatePersonsList(List<Person> personList);

    }

    interface ViewModel {
        LiveData<List<Person>> getPersons();

        LiveData<PersonDetails> getPersonDetail(Integer id);
    }

    interface Model {
        Flowable<List<Person>> getPersons();

        Flowable<PersonDetails> getPersonDetail(Integer id);
    }
}
