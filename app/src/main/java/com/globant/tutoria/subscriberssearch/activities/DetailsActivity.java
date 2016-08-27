package com.globant.tutoria.subscriberssearch.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.globant.tutoria.subscriberssearch.R;
import com.globant.tutoria.subscriberssearch.fragments.SubscribersDetailFragment;

/**
 * Created by juan.herrera@globant.com on 27/08/2016.
 */
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if(savedInstanceState == null){
            SubscribersDetailFragment subscribersDetailFragment = new SubscribersDetailFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container_details, subscribersDetailFragment).commit();

        }
    }
}
