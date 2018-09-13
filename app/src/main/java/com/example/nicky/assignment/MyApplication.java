package com.example.nicky.assignment;

import android.app.Activity;
import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.example.nicky.assignment.di.AppComponent;
import com.example.nicky.assignment.di.AppInjector;
import com.example.nicky.assignment.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by NICKY on 13-09-2018.
 */

public class MyApplication extends MultiDexApplication implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
        appComponent.inject(this);
        AppInjector.init(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
