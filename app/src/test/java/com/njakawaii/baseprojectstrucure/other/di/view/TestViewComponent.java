package com.njakawaii.baseprojectstrucure.other.di.view;


import com.njakawaii.baseprojectstrucure.di.view.ViewComponent;
import com.njakawaii.baseprojectstrucure.view.fragments.RepoListFragmentTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestViewDynamicModule.class})
public interface TestViewComponent extends ViewComponent {

    void inject(RepoListFragmentTest in);

}
