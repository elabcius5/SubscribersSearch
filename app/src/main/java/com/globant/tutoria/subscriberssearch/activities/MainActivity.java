package com.globant.tutoria.subscriberssearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.globant.tutoria.subscriberssearch.R;
import com.globant.tutoria.subscriberssearch.fragments.SubscriberListFragment;

/**
 * Created by juan.herrera@globant.com on 11/08/2016.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            SubscriberListFragment subscriberListFragment = new SubscriberListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container_subscribers_list, subscriberListFragment).commit();
        }
    }
}
