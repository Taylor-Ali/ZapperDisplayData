package com.leaf.zapperdisplaydata.ui.personsdetails;

import androidx.lifecycle.LiveData;

import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;

import io.reactivex.Flowable;

public interface PersonDetailsContract {

    interface View {
        void updatePersonDetails(PersonDetails personDetails);
    }

    interface ViewModel {
        LiveData<PersonDetails> getPersonDetail(Integer id);
    }

    interface Model {
        Flowable<PersonDetails> getPersonDetail(Integer id);
    }
}
