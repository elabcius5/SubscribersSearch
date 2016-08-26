package com.globant.tutoria.subscriberssearch.api;

import com.globant.tutoria.subscriberssearch.api.interfaces.SubscribersInterface;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by juan.herrera@globant.com on 26/08/2016.
 */
public class SubscribersClient {

    private static String API_URL = "https://api.github.com/repos/octokit/octokit.rb/";
    private static volatile SubscribersClient subscribersClient;
    private SubscribersInterface subscribersInterface;

    private SubscribersClient(){
        init();
    }
    public static SubscribersClient getInstance() {
        SubscribersClient localInstance = subscribersClient;
        if (localInstance == null) {
            synchronized (SubscribersClient.class) {
                localInstance = subscribersClient;
                if (localInstance == null) {
                    subscribersClient = localInstance = new SubscribersClient();
                }
            }
        }
        return localInstance;
    }

    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        subscribersInterface = retrofit.create(SubscribersInterface.class);
    }

    public SubscribersInterface getSubscribersService() {
        return subscribersInterface;
    }


}
