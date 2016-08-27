package com.globant.tutoria.subscriberssearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.globant.tutoria.subscriberssearch.R;
import com.globant.tutoria.subscriberssearch.activities.DetailsActivity;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtSingleSubscriber;
        private ImageView imgAvatarSubscriber;
        private Context context;
        private List<SubscribersModel> subscribersModelList;

        public ViewHolder(View view, Context context, List<SubscribersModel> subscribersModelList) {
            super(view);
            this.context = context;
            this.subscribersModelList = subscribersModelList;
            view.setOnClickListener(this);
            this.txtSingleSubscriber = (TextView) view.findViewById(R.id.single_subscriber);
            this.imgAvatarSubscriber = (ImageView)view.findViewById(R.id.img_avatar_subscriber);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            SubscribersModel subscribersModel = this.subscribersModelList.get(position);
            Intent intent = new Intent(this.context, DetailsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("txtLogin", subscribersModel.getLogin());
            intent.putExtra("urlImage", subscribersModel.getAvataUrl());
            this.context.startActivity(intent);
        }
    }

    @Override
    public SubscribersAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_subscriber, viewGroup, false);
        return new ViewHolder(view, this.context, this.subscribersModelList);
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
