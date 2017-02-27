package com.njakawaii.baseprojectstrucure.other;

import com.njakawaii.baseprojectstrucure.di.AppComponent;
import com.njakawaii.baseprojectstrucure.global.App;
import com.njakawaii.baseprojectstrucure.other.di.DaggerTestComponent;

public class TestApplication extends App {

    @Override
    protected AppComponent buildComponent() {
        return DaggerTestComponent.builder()
                .build();
    }
}
