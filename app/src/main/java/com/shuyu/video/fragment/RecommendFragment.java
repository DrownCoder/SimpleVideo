package com.shuyu.video.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuyu.video.R;
import com.shuyu.video.adapter.RecomAdapter;
import com.shuyu.video.api.BaseApi;
import com.shuyu.video.api.IServiceApi;
import com.shuyu.video.model.AppStore;
import com.shuyu.video.model.AppStoreList;

import java.util.List;


public class RecommendFragment extends Fragment {
    private RecyclerView rcl_id_recom;
    private List<AppStore> list;
    private RecomAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        initView(view);
        initDatas();
        return view;
    }

    public void setData(List<AppStore> list){
        this.list = list;
    }

    private void initDatas() {
        adapter = new RecomAdapter(getContext(),list);
        rcl_id_recom.setLayoutManager(new LinearLayoutManager(getContext()));
        rcl_id_recom.setAdapter(adapter);
    }

    private void initView(View view) {
        rcl_id_recom = (RecyclerView) view.findViewById(R.id.rcl_id_recom);
    }

}
