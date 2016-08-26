package com.globant.tutoria.subscriberssearch.api.interfaces;

import com.globant.tutoria.subscriberssearch.api.model.SubscribersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juan.herrera@globant.com on 26/08/2016.
 */
public interface SubscribersInterface {
    @GET("subscribers")
    Call<List<SubscribersModel>> getSubscribers();
}
