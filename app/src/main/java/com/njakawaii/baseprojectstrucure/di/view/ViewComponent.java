package com.njakawaii.baseprojectstrucure.di.view;


import com.njakawaii.baseprojectstrucure.view.fragments.RepoListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewDynamicModule.class})
public interface ViewComponent {

    void inject(RepoListFragment repoListFragment);

}
