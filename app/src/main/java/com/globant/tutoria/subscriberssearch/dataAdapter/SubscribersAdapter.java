package com.globant.tutoria.subscriberssearch.dataAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.globant.tutoria.subscriberssearch.R;
import com.globant.tutoria.subscriberssearch.model.SubscribersModel;

import java.util.List;

/**
 * Created by juan.herrera@globant.com on 20/08/2016.
 */
public class SubscribersAdapter extends RecyclerView.Adapter<SubscribersAdapter.ViewHolder> {

    private List<SubscribersModel> listSubscribers;
    public SubscribersAdapter(List<SubscribersModel> listSubscribers){
        this.listSubscribers = listSubscribers;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSingleSubscriber;

        public ViewHolder(View view) {
            super(view);
            txtSingleSubscriber = (TextView) view.findViewById(R.id.single_subscriber);
        }
    }

    @Override
    public SubscribersAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_subscriber, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubscribersAdapter.ViewHolder viewHolder, int position) {
        viewHolder.txtSingleSubscriber.setText(this.listSubscribers.get(position).getLogin());
    }

    @Override
    public int getItemCount() {
        return this.listSubscribers.size();
    }
}
