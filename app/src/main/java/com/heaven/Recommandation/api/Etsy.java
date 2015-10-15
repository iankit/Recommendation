package com.heaven.Recommandation.api;

import com.heaven.Recommandation.model.ActiveListings;
import com.heaven.Recommandation.model.ListingAdapter;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by Zion on 15/10/15.
 */
public class Etsy {
    public static final String API_KEY = "1r22lp6w8r8u3x3vzc6bob7h";


    private static RequestInterceptor getInterceptor(){
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addEncodedPathParam("api_key",API_KEY);
            }
        };
    }
    private static Api getApi(){
        return new RestAdapter.Builder()
                .setEndpoint("https://openapi.etsy.com/v2")
                .setRequestInterceptor(getInterceptor())
                .build()
                .create(Api.class);
    }
    public static void getActiveListing(ListingAdapter callback){
        getApi().activeListings("Images,Shop", (retrofit.Callback<ActiveListings>) callback);
    }
}
