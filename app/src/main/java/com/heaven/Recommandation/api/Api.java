package com.heaven.Recommandation.api;

import com.heaven.Recommandation.model.ActiveListings;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Zion on 15/10/15.
 */
public interface Api {
    @GET("listings/active")

    void activeListings(@Query("includes")String includes,
                        Callback<ActiveListings> callback);
}
