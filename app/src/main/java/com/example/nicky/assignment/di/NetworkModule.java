package com.example.nicky.assignment.di;

import android.content.Context;

import com.example.nicky.assignment.network.PlanetTypeAdaptorFactory;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import java.io.File;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by NICKY on 13-09-2018.
 */
@Module(includes = ApiModule.class)
public class NetworkModule {
    public static final String PLANET_BASE_URL = "planets/";
    private static final int TIMEOUT_IN_MS = 30000;
    private static final String BASE_URL = "https://swapi.co/api/";

    @Provides
    @Named(PLANET_BASE_URL)
    String provideBaseUrlString() {
        return BASE_URL;
    }

    @Provides
    @Singleton
    CookieManager provideCookieManager() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
        return cookieManager;
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson(TypeAdapterFactory typeAdapterFactory) {
        return new GsonBuilder()
                .registerTypeAdapterFactory(typeAdapterFactory)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    Cache provideCache(Context context) {
        final int cacheSize = 5 * 1024 * 1024; // 5 MB
        File cacheDir = context.getCacheDir();
        return new Cache(cacheDir, cacheSize);
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    @Provides
    @Singleton
    TypeAdapterFactory provideTypeAdapterFactory() {
        return PlanetTypeAdaptorFactory.create();
    }
}
