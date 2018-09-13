package com.example.nicky.assignment.network;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by NICKY on 13-09-2018.
 */

public interface NetworkApi {
    @GET("/shows")
    Single<PlanetResponse> getPlanets(@Query("page") int pageNumber);
   // Single<List<Planets>> getPlanets(@Query("page") int pageNumber);
}
