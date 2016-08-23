package com.globant.tutoria.subscriberssearch.dataAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.globant.tutoria.subscriberssearch.R;
import com.globant.tutoria.subscriberssearch.model.SubscribersModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by juan.herrera@globant.com on 20/08/2016.
 */
public class DataSubscribersAdapter extends RecyclerView.Adapter<DataSubscribersAdapter.ViewHolder> {

    private static final int WIDTH_IMG_AVATAR = 80;
    private static final int HEIGHT_IMG_AVATAR = 80;
    private List<SubscribersModel> listSubscribers;
    private Context context;
    public DataSubscribersAdapter (Context context, List<SubscribersModel> listSubscribers){
        this.listSubscribers = listSubscribers;
        this.context = context;
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
    public DataSubscribersAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_subscriber, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataSubscribersAdapter.ViewHolder viewHolder, int position) {
        viewHolder.txtSingleSubscriber.setText(this.listSubscribers.get(position).getLogin());
        Picasso.with(this.context).load(this.listSubscribers.get(position).getAvataUrl()).resize(WIDTH_IMG_AVATAR, HEIGHT_IMG_AVATAR).into(viewHolder.imgAvatarSubscriber);
    }

    @Override
    public int getItemCount() {
        return this.listSubscribers.size();
    }
}
