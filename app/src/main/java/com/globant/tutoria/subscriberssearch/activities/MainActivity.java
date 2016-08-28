package com.globant.tutoria.subscriberssearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

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
            getSupportFragmentManager().beginTransaction().add(R.id.container, subscriberListFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
