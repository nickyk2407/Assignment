package com.example.nicky.assignment.di;

import com.example.nicky.assignment.network.NetworkApi;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NICKY on 13-09-2018.
 */

@Module
public class ApiModule {
    @Provides
    @Singleton
    public NetworkApi provideNetworkApi(OkHttpClient okHttpClient,
                                        @Named(NetworkModule.PLANET_BASE_URL) String baseUrl,
                                        RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                                        Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build().create(NetworkApi.class);

    }

}
