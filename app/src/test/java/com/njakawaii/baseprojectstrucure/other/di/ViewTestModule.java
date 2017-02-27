package com.njakawaii.baseprojectstrucure.other.di;

import com.njakawaii.baseprojectstrucure.presenter.RepoInfoPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class ViewTestModule {

    @Provides
    @Singleton
    RepoInfoPresenter provideRepoInfoPresenter() {
        return mock(RepoInfoPresenter.class);
    }

}
