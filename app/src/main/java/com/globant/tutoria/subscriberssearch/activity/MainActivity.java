package com.globant.tutoria.subscriberssearch.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

import com.globant.tutoria.subscriberssearch.R;
import com.globant.tutoria.subscriberssearch.model.SubscribersModel;
import com.globant.tutoria.subscriberssearch.rest.OctokitInterface;
import com.globant.tutoria.subscriberssearch.rest.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by juan.herrera@globant.com on 11/08/2016.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OctokitInterface octokitInterface = RetrofitClient.getClient().create(OctokitInterface.class);
        Call<List<SubscribersModel>> callService = octokitInterface.getSubscribers();
        callService.enqueue(new Callback<List<SubscribersModel>>() {
            @Override
            public void onResponse(Call<List<SubscribersModel>> call, Response<List<SubscribersModel>> response) {
                List<SubscribersModel> listSubscribers = response.body();
                for(SubscribersModel subscribersModel : listSubscribers){
                    Log.d("onResponse login() ", subscribersModel.getLogin());
                    Log.d("onResponse avataUrl() ", subscribersModel.getAvataUrl());
                }
            }

            @Override
            public void onFailure(Call<List<SubscribersModel>> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
