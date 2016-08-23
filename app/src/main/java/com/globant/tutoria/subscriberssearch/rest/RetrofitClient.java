package com.globant.tutoria.subscriberssearch.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by juan.herrera@globant.com on 18/08/2016.
 */
public class RetrofitClient {

    public static final String URL_OCTOKIT = "https://api.github.com/repos/octokit/octokit.rb/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_OCTOKIT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
