package com.example.nicky.assignment.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NICKY on 13-09-2018.
 */
@Module(includes = ViewModelModule.class)
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }
}
