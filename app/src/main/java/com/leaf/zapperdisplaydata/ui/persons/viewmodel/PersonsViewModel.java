package com.leaf.zapperdisplaydata.ui.persons.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leaf.zapperdisplaydata.ZapperDisplayDataApplication;
import com.leaf.zapperdisplaydata.data.remote.model.Person;
import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;
import com.leaf.zapperdisplaydata.ui.persons.PersonsContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PersonsViewModel extends ViewModel implements PersonsContract.ViewModel {
    @Inject
    PersonsContract.Model model;
    private CompositeDisposable subscriptions = new CompositeDisposable();
    private MutableLiveData<List<Person>> listMutableLiveData;
    private MutableLiveData<PersonDetails> personDetailsMutableLiveData;


    public PersonsViewModel(PersonsContract.Model model) {
        this.model = model;
    }

    public PersonsViewModel() {
        ZapperDisplayDataApplication.getComponent().personsComponent()
                .inject(this);

        listMutableLiveData = new MutableLiveData<>();
        personDetailsMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<Person>> getPersons() {
        addViewModelSubscription(
                model.getPersons()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(persons -> {
                            Log.v(PersonsViewModel.class.getSimpleName(), persons.toString());
                            listMutableLiveData.setValue(persons);

                        }, Throwable::printStackTrace));

        return listMutableLiveData;
    }

    @Override
    public LiveData<PersonDetails> getPersonDetail(Integer id) {
        addViewModelSubscription(model.getPersonDetail(id)
                .subscribe(personDetails -> {
                    personDetailsMutableLiveData.setValue(personDetails);
                }, Throwable::printStackTrace));
        return personDetailsMutableLiveData;
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
