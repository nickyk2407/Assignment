package com.example.nicky.assignment.datasource;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.nicky.assignment.network.NetworkApi;
import com.example.nicky.assignment.network.Planets;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by NICKY on 13-09-2018.
 */
@Singleton
public class PlanetDataSource extends ItemKeyedDataSource<Integer, Planets> {

    private final NetworkApi networkApi;
    private CompositeDisposable compositeDisposable;
    private int pageNumber = 1;
    private MutableLiveData<NetworkState> paginatedNetworkStateLiveData;
    private MutableLiveData<NetworkState> initialLoadStateLiveData;
    private LoadParams<Integer> params;
    private LoadCallback<Planets> callback;

    @Inject
    public PlanetDataSource(NetworkApi networkApi) {
        this.networkApi = networkApi;
        compositeDisposable = new CompositeDisposable();
        initialLoadStateLiveData = new MutableLiveData<>();
        paginatedNetworkStateLiveData = new MutableLiveData<>();

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Planets> callback) {
        initialLoadStateLiveData.postValue(NetworkState.builder()
                .status(NetworkState.Status.LOADING).build());
        Disposable disposable = networkApi.getPlanets(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(planetResponse -> onPlanetFetched(planetResponse.getResults(), callback), this::onError);
        compositeDisposable.add(disposable);
    }

    private void onError(Throwable throwable) {
        initialLoadStateLiveData.postValue(NetworkState.builder()
                .status(NetworkState.Status.ERROR)
                .message(throwable.getMessage()).build());
    }

    private void onPlanetFetched(List<Planets> planets, LoadInitialCallback<Planets> callback) {
        initialLoadStateLiveData.postValue(NetworkState.builder()
                .status(NetworkState.Status.SUCCESS).build());
        pageNumber++;
        callback.onResult(planets);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Planets> callback) {
        this.params = params;
        this.callback = callback;
        paginatedNetworkStateLiveData.postValue(NetworkState.builder()
                .status(NetworkState.Status.LOADING).build());
        Disposable showsDisposable = networkApi.getPlanets(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(planetResponse -> onMoreShowsFetched(planetResponse.getResults(), callback), this::onPaginationError);
        compositeDisposable.add(showsDisposable);

    }

    private void onPaginationError(Throwable throwable) {
        paginatedNetworkStateLiveData.postValue(NetworkState.builder()
                .status(NetworkState.Status.ERROR)
                .message(throwable.getMessage()).build());
    }

    private void onMoreShowsFetched(List<Planets> planets, LoadCallback<Planets> callback) {
        paginatedNetworkStateLiveData.postValue(NetworkState.builder()
                .status(NetworkState.Status.SUCCESS).build());
        pageNumber++;
        callback.onResult(planets);

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Planets> callback) {

    }

    @NonNull
    @Override
    public Integer getKey(@NonNull Planets item) {
        return pageNumber;
    }

    public void clear() {
        compositeDisposable.clear();
        pageNumber = 1;
    }

    public LiveData<NetworkState> getPaginatedNetworkStateLiveData() {
        return paginatedNetworkStateLiveData;
    }

    public LiveData<NetworkState> getInitialLoadStateLiveData() {
        return initialLoadStateLiveData;
    }

    public void retryPagination() {
        loadAfter(params, callback);
    }
}
