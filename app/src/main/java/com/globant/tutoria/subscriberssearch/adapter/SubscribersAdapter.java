package com.globant.tutoria.subscriberssearch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.globant.tutoria.subscriberssearch.R;
import com.globant.tutoria.subscriberssearch.api.model.SubscribersModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan.herrera@globant.com on 20/08/2016.
 */
public class SubscribersAdapter extends RecyclerView.Adapter<SubscribersAdapter.ViewHolder> {

    private static final int WIDTH_IMG_AVATAR = 80;
    private static final int HEIGHT_IMG_AVATAR = 80;
    private List<SubscribersModel> subscribersModelList;
    private Context context;

    public SubscribersAdapter(Context context){
        this.subscribersModelList = new ArrayList<>();
        this.context = context;
    }

    public void setItems(List<SubscribersModel> subscribersModelList){
        this.subscribersModelList.clear();
        this.subscribersModelList.addAll(subscribersModelList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSingleSubscriber;
        private ImageView imgAvatarSubscriber;

        public ViewHolder(View view) {
            super(view);
            txtSingleSubscriber = (TextView) view.findViewById(R.id.single_subscriber);
            imgAvatarSubscriber = (ImageView)view.findViewById(R.id.img_avatar_subscriber);
        }
    }

    @Override
    public SubscribersAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_subscriber, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubscribersAdapter.ViewHolder viewHolder, int position) {
        viewHolder.txtSingleSubscriber.setText(this.subscribersModelList.get(position).getLogin());
        Picasso.with(this.context).load(this.subscribersModelList.get(position).getAvataUrl()).resize(WIDTH_IMG_AVATAR, HEIGHT_IMG_AVATAR).into(viewHolder.imgAvatarSubscriber);
    }

    @Override
    public int getItemCount() {
        return this.subscribersModelList.size();
    }
}
