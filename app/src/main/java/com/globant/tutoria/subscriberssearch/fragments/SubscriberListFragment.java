package com.globant.tutoria.subscriberssearch.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.globant.tutoria.subscriberssearch.R;
import com.globant.tutoria.subscriberssearch.adapter.SubscribersAdapter;
import com.globant.tutoria.subscriberssearch.api.model.SubscribersModel;
import com.globant.tutoria.subscriberssearch.manager.SubscribersManager;

import java.util.List;

/**
 * Created by juan.herrera@globant.com on 20/08/2016.
 */
public class SubscriberListFragment extends Fragment implements SubscribersManager.SubscribersCallbackListener {

    private static final String TAG = "RecyclerViewFragmentSubscribers";
    protected RecyclerView recyclerViewSubscribers;
    private SubscribersAdapter subscribersAdapter;
    private SubscribersManager subscribersManager;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_toggle_log:
                Log.e("menu", "CLICK");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        subscribersAdapter = new SubscribersAdapter(getActivity().getApplicationContext());
        subscribersManager = new SubscribersManager(SubscriberListFragment.this);
        subscribersManager.getSubscribersList();
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

    @Override
    public void onFetchProgress(List<SubscribersModel> subscribersModelList) {
        subscribersAdapter.setItems(subscribersModelList);
    }

    @Override
    public void onFetchFailed(String errorMessage) {
        Log.e("onFailure", errorMessage);
    }
}
