package com.example.nicky.assignment.datasource;


import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.nicky.assignment.network.Planets;

import javax.inject.Inject;

/**
 * Created by NICKY on 13-09-2018.
 */

public class PlanetDataSourceFactory extends DataSource.Factory<Integer, Planets> {
    private final PlanetDataSource planetDataSource;
    private final MutableLiveData<PlanetDataSource> planetDataSourceLiveData;

    @Inject
    public PlanetDataSourceFactory(PlanetDataSource planetDataSource) {
        this.planetDataSource = planetDataSource;
        planetDataSourceLiveData = new MutableLiveData<>();

    }

    @Override
    public DataSource<Integer, Planets> create() {
        planetDataSourceLiveData.postValue(planetDataSource);
        return planetDataSource;
    }

    public PlanetDataSource getPlanetDataSource() {
        return planetDataSource;
    }
}
