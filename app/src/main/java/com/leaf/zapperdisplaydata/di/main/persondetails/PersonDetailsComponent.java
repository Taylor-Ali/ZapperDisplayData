package com.leaf.zapperdisplaydata.di.main.persondetails;

import com.leaf.zapperdisplaydata.ui.personsdetails.viewmodel.PersonDetailsViewModel;

import dagger.Subcomponent;

@Subcomponent(modules = {PersonDetailsModule.class})
public interface PersonDetailsComponent {
    void inject(PersonDetailsViewModel personDetailsViewModel);
}
