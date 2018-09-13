package com.example.nicky.assignment.viewmodule;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.nicky.assignment.datasource.NetworkState;
import com.example.nicky.assignment.datasource.PlanetDataSourceFactory;
import com.example.nicky.assignment.network.Planets;

import javax.inject.Inject;

/**
 * Created by NICKY on 13-09-2018.
 */

public class PlanetListViewModule extends ViewModel {

    private final PlanetDataSourceFactory dataSourceFactory;
    private LiveData<PagedList<Planets>> planets;

    @Inject
    PlanetListViewModule(PlanetDataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    public void onScreenCreated() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(40)
                .build();
        planets = new LivePagedListBuilder<>(dataSourceFactory, config).build();
    }

    public LiveData<PagedList<Planets>> getPlanets() {
        return planets;
    }

    public LiveData<NetworkState> initialLoadState() {
        return dataSourceFactory.getPlanetDataSource().getInitialLoadStateLiveData();
    }

    public LiveData<NetworkState> paginatedLoadState() {
        return dataSourceFactory.getPlanetDataSource().getPaginatedNetworkStateLiveData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        dataSourceFactory.getPlanetDataSource().clear();
    }

    public void retry() {
        dataSourceFactory.getPlanetDataSource().retryPagination();
    }


}
