package com.shuyu.video.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuyu.video.R;
import com.shuyu.video.activity.CustomActivity;
import com.shuyu.video.adapter.UserListAdapter;


public class MeFragment extends Fragment {
    private RecyclerView rcl_id_user;
    private ImageView iv_id_custom;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        iv_id_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CustomActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        rcl_id_user.setLayoutManager(new LinearLayoutManager(getContext()));
        rcl_id_user.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        rcl_id_user.setAdapter(new UserListAdapter(getContext()));
    }

    private void initView(View view) {
        rcl_id_user = (RecyclerView) view.findViewById(R.id.rcl_id_user);
        iv_id_custom = (ImageView) view.findViewById(R.id.iv_id_custom);
    }

}
