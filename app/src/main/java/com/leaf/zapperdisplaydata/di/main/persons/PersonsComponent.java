package com.leaf.zapperdisplaydata.di.main.persons;

import com.leaf.zapperdisplaydata.ui.persons.viewmodel.PersonsViewModel;

import dagger.Subcomponent;

@Subcomponent(modules = {PersonsModule.class})
public interface PersonsComponent {
    void inject(PersonsViewModel personsViewModel);
}
