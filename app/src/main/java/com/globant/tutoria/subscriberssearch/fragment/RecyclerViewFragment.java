package com.globant.tutoria.subscriberssearch.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.tutoria.subscriberssearch.R;
import com.globant.tutoria.subscriberssearch.dataAdapter.DataSubscribersAdapter;
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
public class RecyclerViewFragment extends Fragment {

    private static final String TAG = "RecyclerViewFragmentSubscribers";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView recyclerViewSubscribers;
    protected RecyclerView.LayoutManager mLayoutManager;
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        rootView.setTag(TAG);
        recyclerViewSubscribers = (RecyclerView) rootView.findViewById(R.id.recycler_view_subscribers);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        OctokitInterface octokitInterface = RetrofitClient.getClient().create(OctokitInterface.class);
        Call<List<SubscribersModel>> callService = octokitInterface.getSubscribers();
        callService.enqueue(new Callback<List<SubscribersModel>>() {
            @Override
            public void onResponse(Call<List<SubscribersModel>> call, Response<List<SubscribersModel>> response) {
                List<SubscribersModel> listSubscribers = response.body();
                RecyclerView.Adapter adapter = new DataSubscribersAdapter(listSubscribers);
                recyclerViewSubscribers.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<SubscribersModel>> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
        return rootView;
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;
        if (recyclerViewSubscribers.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerViewSubscribers.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }
        recyclerViewSubscribers.setLayoutManager(mLayoutManager);
        recyclerViewSubscribers.scrollToPosition(scrollPosition);
    }
}
