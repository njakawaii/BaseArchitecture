package com.njakawaii.baseprojectstrucure.other.di;


import com.njakawaii.baseprojectstrucure.di.AppComponent;
import com.njakawaii.baseprojectstrucure.model.ModelImplTest;
import com.njakawaii.baseprojectstrucure.presenter.RepoInfoPresenterTest;
import com.njakawaii.baseprojectstrucure.presenter.RepoListPresenterTest;
import com.njakawaii.baseprojectstrucure.presenter.mappers.RepoBranchesMapperTest;
import com.njakawaii.baseprojectstrucure.presenter.mappers.RepoContributorsMapperTest;
import com.njakawaii.baseprojectstrucure.presenter.mappers.RepoListMapperTest;
import com.njakawaii.baseprojectstrucure.view.fragments.BaseFragmentTest;
import com.njakawaii.baseprojectstrucure.view.fragments.RepoInfoFragmentTest;
import com.njakawaii.baseprojectstrucure.view.fragments.RepoListFragmentTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelTestModule.class, PresenterTestModule.class, ViewTestModule.class, DataTestModule.class})
public interface TestComponent extends AppComponent {


    void inject(ModelImplTest dataRepositoryImplTest);

    void inject(RepoInfoPresenterTest repoInfoPresenterTest);

    void inject(RepoListPresenterTest repoListPresenterTest);

    void inject(RepoBranchesMapperTest repoBranchesMapperTest);

    void inject(RepoContributorsMapperTest repoContributorsMapperTest);

    void inject(RepoListMapperTest userReposMapperTest);

    void inject(RepoInfoFragmentTest repoInfoFragmentTest);

    void inject(RepoListFragmentTest repoListFragmentTest);

    void inject(BaseFragmentTest baseFragmentTest);
}
