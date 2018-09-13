package com.example.nicky.assignment.di;

import android.app.Application;

import com.example.nicky.assignment.MyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by NICKY on 13-09-2018.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class, NetworkModule.class,
        ActivityBuildersModule.class})
public interface AppComponent {
    void inject(MyApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
