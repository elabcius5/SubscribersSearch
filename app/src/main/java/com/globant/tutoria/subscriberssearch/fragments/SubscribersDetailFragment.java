package com.globant.tutoria.subscriberssearch.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.globant.tutoria.subscriberssearch.R;
import com.squareup.picasso.Picasso;

/**
 * Created by juan.herrera@globant.com on 27/08/2016.
 */
public class SubscribersDetailFragment extends Fragment {

    private static final int WIDTH_IMG_AVATAR = 150;
    private static final int HEIGHT_IMG_AVATAR = 150;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_fragment, container, false);
        Picasso.with(getContext()).load(getActivity().getIntent().getExtras().getString("urlImage")).resize(WIDTH_IMG_AVATAR, HEIGHT_IMG_AVATAR).into((ImageView) rootView.findViewById(R.id.image_avatar_detail));
        TextView txtLogin = (TextView) rootView.findViewById(R.id.txt_login_detail);
        txtLogin.setText(getActivity().getIntent().getExtras().getString("txtLogin"));
        return rootView;
    }
}
