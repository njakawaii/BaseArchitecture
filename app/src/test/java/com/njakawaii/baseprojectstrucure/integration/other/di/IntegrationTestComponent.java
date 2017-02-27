package com.njakawaii.baseprojectstrucure.integration.other.di;


import com.njakawaii.baseprojectstrucure.di.AppComponent;
import com.njakawaii.baseprojectstrucure.di.PresenterModule;
import com.njakawaii.baseprojectstrucure.di.ViewModule;
import com.njakawaii.baseprojectstrucure.integration.model.ModelTest;
import com.njakawaii.baseprojectstrucure.integration.other.IntegrationBaseTest;
import com.njakawaii.baseprojectstrucure.integration.presenter.RepoInfoPresenterTest;
import com.njakawaii.baseprojectstrucure.integration.presenter.RepoListPresenterTest;
import com.njakawaii.baseprojectstrucure.integration.view.RepoInfoFragmentTest;
import com.njakawaii.baseprojectstrucure.integration.view.RepoListFragmentTest;
import com.njakawaii.baseprojectstrucure.other.di.DataTestModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {IntegrationTestModelModule.class, PresenterModule.class, ViewModule.class, DataTestModule.class})
public interface IntegrationTestComponent extends AppComponent {

    void inject(ModelTest modelTest);

    void inject(RepoInfoPresenterTest repoInfoPresenterTest);

    void inject(RepoListPresenterTest repoListPresenterTest);

    void inject(RepoInfoFragmentTest repoInfoFragmentTest);

    void inject(RepoListFragmentTest repoListFragmentTest);

    void inject(IntegrationBaseTest integrationBaseTest);

}
