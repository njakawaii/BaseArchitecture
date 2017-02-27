package com.njakawaii.baseprojectstrucure.di;


import com.njakawaii.baseprojectstrucure.model.Model;
import com.njakawaii.baseprojectstrucure.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module

public class PresenterModule {

    @Provides
    @Singleton
    Model provideDataRepository() {
        return new ModelImpl();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
