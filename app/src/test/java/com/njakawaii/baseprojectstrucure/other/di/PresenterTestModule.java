package com.njakawaii.baseprojectstrucure.other.di;

import com.njakawaii.baseprojectstrucure.model.Model;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

import static org.mockito.Mockito.mock;

@Module

public class PresenterTestModule {

    @Provides
    @Singleton
    Model provideDataRepository() {
        return mock(Model.class);
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
