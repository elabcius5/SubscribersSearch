package com.globant.tutoria.subscriberssearch.manager;

import com.globant.tutoria.subscriberssearch.api.SubscribersClient;
import com.globant.tutoria.subscriberssearch.api.model.SubscribersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by juan.herrera@globant.com on 26/08/2016.
 */
public class SubscribersManager {

    private SubscribersClient sSubscribersClient;
    private SubscribersCallbackListener subscribersCallbackListener;

    public SubscribersManager(SubscribersCallbackListener subscribersCallbackListener){
        this.sSubscribersClient = SubscribersClient.getInstance();
        this.subscribersCallbackListener = subscribersCallbackListener;
    }

    public void getSubscribersList(){
        sSubscribersClient.getSubscribersService().getSubscribers().enqueue(new Callback<List<SubscribersModel>>() {
            @Override
            public void onResponse(Call<List<SubscribersModel>> call, Response<List<SubscribersModel>> response) {
                subscribersCallbackListener.onFetchProgress(response.body());
            }

            @Override
            public void onFailure(Call<List<SubscribersModel>> call, Throwable t) {
                subscribersCallbackListener.onFetchFailed(t.getMessage());
            }
        });
    }

    public interface SubscribersCallbackListener {
        void onFetchProgress(List<SubscribersModel> subscribersModelList);
        void onFetchFailed(String errorMessage);
    }
}
