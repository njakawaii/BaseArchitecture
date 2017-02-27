package com.njakawaii.baseprojectstrucure.other.di.view;


import com.njakawaii.baseprojectstrucure.presenter.RepoListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class TestViewDynamicModule {

    @Provides
    @Singleton
    RepoListPresenter provideRepoListPresenter() {
        return mock(RepoListPresenter.class);
    }

}
