package com.leaf.zapperdisplaydata.di.main.persondetails;

import com.leaf.zapperdisplaydata.data.remote.PersonsApi;
import com.leaf.zapperdisplaydata.ui.personsdetails.PersonDetailsContract;
import com.leaf.zapperdisplaydata.ui.personsdetails.model.PersonDetailsModel;
import com.leaf.zapperdisplaydata.ui.personsdetails.viewmodel.PersonDetailsViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class PersonDetailsModule {

    @Provides
    PersonDetailsContract.Model providesPersonDetailsModel(PersonsApi personsApi) {
        return new PersonDetailsModel(personsApi);
    }

    @Provides
    PersonDetailsContract.ViewModel providesPersonDetailsViewModel(PersonDetailsContract.Model model) {
        return new PersonDetailsViewModel(model);
    }

}
