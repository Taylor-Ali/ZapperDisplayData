package com.leaf.zapperdisplaydata.di.main.persons;

import com.leaf.zapperdisplaydata.data.PersonsDataLayer;
import com.leaf.zapperdisplaydata.ui.persons.PersonsContract;
import com.leaf.zapperdisplaydata.ui.persons.model.PersonsModel;
import com.leaf.zapperdisplaydata.ui.persons.viewmodel.PersonsViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class PersonsModule {
    @Provides
    PersonsContract.Model providesPersonsModel(PersonsDataLayer personsDataLayer) {
        return new PersonsModel(personsDataLayer);
    }

    @Provides
    PersonsContract.ViewModel providesPersonsViewModel(PersonsContract.Model model) {
        return new PersonsViewModel(model);
    }
}
