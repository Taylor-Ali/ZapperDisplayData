package com.leaf.zapperdisplaydata.ui.personsdetails.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leaf.zapperdisplaydata.ZapperDisplayDataApplication;
import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;
import com.leaf.zapperdisplaydata.ui.personsdetails.PersonDetailsContract;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class PersonDetailsViewModel extends ViewModel implements PersonDetailsContract.ViewModel {
    @Inject
    PersonDetailsContract.Model personDetailsModel;
    private MutableLiveData<PersonDetails> listMutableLiveData;
    private CompositeDisposable subscriptions = new CompositeDisposable();


    public PersonDetailsViewModel(PersonDetailsContract.Model personDetailsModel) {
        this.personDetailsModel = personDetailsModel;
    }

    public PersonDetailsViewModel() {
        ZapperDisplayDataApplication.getComponent().personDetailsComponent()
                .inject(this);

        listMutableLiveData = new MutableLiveData<>();
    }


    @Override
    public LiveData<PersonDetails> getPersonDetail(Integer id) {
        addViewModelSubscription(personDetailsModel.getPersonDetail(id)
                .subscribe(personDetails -> {
                    listMutableLiveData.setValue(personDetails);
                }, Throwable::printStackTrace));
        return listMutableLiveData;
    }

    private void addViewModelSubscription(Disposable disposable) {
        subscriptions.add(disposable);
    }

    private void clearViewModelSubscription() {
        subscriptions.clear();
    }

    @Override
    protected void onCleared() {
        clearViewModelSubscription();
        super.onCleared();
    }
}
