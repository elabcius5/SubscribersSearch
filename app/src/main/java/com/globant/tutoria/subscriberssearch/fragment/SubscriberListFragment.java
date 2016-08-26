package com.globant.tutoria.subscriberssearch.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.globant.tutoria.subscriberssearch.R;
import com.globant.tutoria.subscriberssearch.dataAdapter.SubscribersAdapter;
import com.globant.tutoria.subscriberssearch.model.SubscribersModel;
import com.globant.tutoria.subscriberssearch.rest.OctokitInterface;
import com.globant.tutoria.subscriberssearch.rest.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by juan.herrera@globant.com on 20/08/2016.
 */
public class SubscriberListFragment extends Fragment {

    private static final String TAG = "RecyclerViewFragmentSubscribers";
    protected RecyclerView recyclerViewSubscribers;

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

    ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        rootView.setTag(TAG);
        recyclerViewSubscribers = (RecyclerView) rootView.findViewById(R.id.recycler_view_subscribers);
        progressDialog.show();
        progressDialog.setMessage("Loading subscribers");
        recyclerViewSubscribers.setLayoutManager(new LinearLayoutManager(getActivity()));
        OctokitInterface octokitInterface = RetrofitClient.getClient().create(OctokitInterface.class);
        Call<List<SubscribersModel>> callService = octokitInterface.getSubscribers();
        callService.enqueue(new Callback<List<SubscribersModel>>() {
            @Override
            public void onResponse(Call<List<SubscribersModel>> call, Response<List<SubscribersModel>> response) {
                List<SubscribersModel> listSubscribers = response.body();
                RecyclerView.Adapter adapter = new SubscribersAdapter(getActivity().getApplicationContext(), listSubscribers);
                recyclerViewSubscribers.setAdapter(adapter);
                progressDialog.hide();
            }
            @Override
            public void onFailure(Call<List<SubscribersModel>> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });

        return rootView;
    }
}
