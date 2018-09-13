package com.example.nicky.assignment.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.nicky.assignment.viewmodule.PlanetListViewModule;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by NICKY on 13-09-2018.
 */
@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PlanetListViewModule.class)
    abstract ViewModel bindPlanetViewModule(PlanetListViewModule planetListModule);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(PlanetsViewModelFactory factory);
}
