package com.njakawaii.baseprojectstrucure.integration.other;


import com.njakawaii.baseprojectstrucure.di.AppComponent;
import com.njakawaii.baseprojectstrucure.global.App;
import com.njakawaii.baseprojectstrucure.integration.other.di.DaggerIntegrationTestComponent;

public class IntegrationTestApp extends App {

    @Override
    protected AppComponent buildComponent() {
        return DaggerIntegrationTestComponent.builder()
                .build();
    }
}
