package com.njakawaii.baseprojectstrucure.di.view;


import com.njakawaii.baseprojectstrucure.presenter.RepoListPresenter;
import com.njakawaii.baseprojectstrucure.view.fragments.RepoListView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicModule {

    private RepoListView view;

    public ViewDynamicModule(RepoListView view) {
        this.view = view;
    }

    @Provides
    RepoListPresenter provideRepoListPresenter() {
        return new RepoListPresenter(view);
    }

}
