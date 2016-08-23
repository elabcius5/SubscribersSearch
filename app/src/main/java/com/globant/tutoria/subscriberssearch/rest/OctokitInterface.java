package com.globant.tutoria.subscriberssearch.rest;

import com.globant.tutoria.subscriberssearch.model.SubscribersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juan.herrera@globant.com on 18/08/2016.
 */
public interface OctokitInterface {

    @GET("subscribers")
    Call<List<SubscribersModel>> getSubscribers();
}
