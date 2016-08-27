package com.globant.tutoria.subscriberssearch.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.tutoria.subscriberssearch.R;
import com.globant.tutoria.subscriberssearch.adapter.SubscribersAdapter;
import com.globant.tutoria.subscriberssearch.bus.BusProvider;
import com.globant.tutoria.subscriberssearch.events.SendSubscribersEvent;
import com.globant.tutoria.subscriberssearch.manager.SubscribersManager;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by juan.herrera@globant.com on 20/08/2016.
 */
public class SubscriberListFragment extends Fragment {

    private static final String TAG = "RecyclerViewFragmentSubscribers";
    protected RecyclerView recyclerViewSubscribers;
    private SubscribersAdapter subscribersAdapter;
    private SubscribersManager mSubscribersManager;
    private Bus mBus = BusProvider.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribersAdapter = new SubscribersAdapter(getActivity().getApplicationContext());
        mSubscribersManager = new SubscribersManager(mBus);
        mSubscribersManager.getSubscribers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subscribers_list_fragment, container, false);
        rootView.setTag(TAG);
        recyclerViewSubscribers = (RecyclerView) rootView.findViewById(R.id.recycler_view_subscribers);
        recyclerViewSubscribers.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSubscribers.setAdapter(subscribersAdapter);
        return rootView;
    }

    @Override public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onSendSubscriberEvent(SendSubscribersEvent sendSubscribersEvent){
        subscribersAdapter.setItems(sendSubscribersEvent.getSubscribersModelList());
    }
}