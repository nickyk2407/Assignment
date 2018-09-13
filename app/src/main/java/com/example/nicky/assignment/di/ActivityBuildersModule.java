package com.example.nicky.assignment.di;


import com.example.nicky.assignment.ui.PlanetDetailActivity;
import com.example.nicky.assignment.ui.PlanetListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by NICKY on 13-09-2018.
 */
@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract PlanetListActivity bindPlanetListActivity();

    @ContributesAndroidInjector
    abstract PlanetDetailActivity bindPlanetDetailActivity();
}
