package com.globant.tutoria.subscriberssearch.application;

import android.app.Application;

import com.globant.tutoria.subscriberssearch.bus.BusProvider;
import com.globant.tutoria.subscriberssearch.manager.SubscribersManager;
import com.squareup.otto.Bus;

/**
 * Created by juan.herrera@globant.com on 26/08/2016.
 */
public class SubscribersApplication extends Application {

    private SubscribersManager mSubscribersManager;
    private Bus mBus = BusProvider.getInstance();

    @Override
    public void onCreate() {
        super.onCreate();
        mSubscribersManager = new SubscribersManager(mBus);
        mSubscribersManager.onGetSubscribersEvent();
    }
}
