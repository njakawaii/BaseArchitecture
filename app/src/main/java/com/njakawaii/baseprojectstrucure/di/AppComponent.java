package com.njakawaii.baseprojectstrucure.di;

import com.njakawaii.baseprojectstrucure.model.ModelImpl;
import com.njakawaii.baseprojectstrucure.presenter.BasePresenter;
import com.njakawaii.baseprojectstrucure.presenter.RepoInfoPresenter;
import com.njakawaii.baseprojectstrucure.presenter.RepoListPresenter;
import com.njakawaii.baseprojectstrucure.view.fragments.RepoInfoFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {

    void inject(ModelImpl dataRepository);

    void inject(BasePresenter basePresenter);

    void inject(RepoListPresenter repoListPresenter);

    void inject(RepoInfoPresenter repoInfoPresenter);

    void inject(RepoInfoFragment repoInfoFragment);

}
