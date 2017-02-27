package com.njakawaii.baseprojectstrucure.di;

import com.njakawaii.baseprojectstrucure.presenter.RepoInfoPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    RepoInfoPresenter provideRepoInfoPresenter() {
        return new RepoInfoPresenter();
    }

}
