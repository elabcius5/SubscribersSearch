package com.globant.tutoria.subscriberssearch.manager;

import android.util.Log;

import com.globant.tutoria.subscriberssearch.api.SubscribersClient;
import com.globant.tutoria.subscriberssearch.api.model.SubscribersModel;
import com.globant.tutoria.subscriberssearch.events.SendSubscribersEvent;
import com.squareup.otto.Bus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by juan.herrera@globant.com on 26/08/2016.
 */
public class SubscribersManager {
    private Bus mBus;
    private SubscribersClient sSubscribersClient;

    public SubscribersManager(Bus bus){
        this.mBus = bus;
        this.sSubscribersClient = SubscribersClient.getInstance();
    }

    public void getSubscribers(){
        sSubscribersClient.getSubscribersService().getSubscribers().enqueue(new Callback<List<SubscribersModel>>() {
            @Override
            public void onResponse(Call<List<SubscribersModel>> call, Response<List<SubscribersModel>> response) {
                mBus.post(new SendSubscribersEvent(response.body()));
            }

            @Override
            public void onFailure(Call<List<SubscribersModel>> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

}
