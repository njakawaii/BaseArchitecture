package com.njakawaii.baseprojectstrucure.global;

import android.app.Application;

import com.njakawaii.baseprojectstrucure.di.AppComponent;
import com.njakawaii.baseprojectstrucure.di.DaggerAppComponent;

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .build();
    }


}
