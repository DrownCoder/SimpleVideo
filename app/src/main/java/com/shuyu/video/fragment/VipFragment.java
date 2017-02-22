package com.shuyu.video.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalong.francyconverflow.FancyCoverFlow;
import com.shuyu.video.R;
import com.shuyu.video.adapter.MyFancyCoverFlowAdapter;
import com.shuyu.video.model.VideoPicDetails;

import java.util.ArrayList;
import java.util.List;


public class VipFragment extends Fragment {
    private FancyCoverFlow mfancyCoverFlow;
    private MyFancyCoverFlowAdapter mMyFancyCoverFlowAdapter;
    private List<VideoPicDetails> liveVideo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vip, container, false);

        mfancyCoverFlow = (FancyCoverFlow) view.findViewById(R.id.fancyCoverFlow);
/*        List<Item> mFancyCoverFlows=new ArrayList<>();
        for(int i=0;i<365;i++){
            Item item=new Item();
            item.setName((i+1)+"天");
            item.setSelected(false);
            mFancyCoverFlows.add(item);
        }*/
        initGalleryViewPager(liveVideo);

        return view;
    }

    public void setData(List<VideoPicDetails> data){
        this.liveVideo = data;
    }

    private void initGalleryViewPager(List<VideoPicDetails> mFancyCoverFlows) {
        mMyFancyCoverFlowAdapter = new MyFancyCoverFlowAdapter(getContext(), mFancyCoverFlows);
        mfancyCoverFlow.setAdapter(mMyFancyCoverFlowAdapter);
        mMyFancyCoverFlowAdapter.notifyDataSetChanged();
        mfancyCoverFlow.setUnselectedAlpha(0.5f);//通明度
        mfancyCoverFlow.setUnselectedSaturation(0.5f);//设置选中的饱和度
        mfancyCoverFlow.setUnselectedScale(0.3f);//设置选中的规模
        mfancyCoverFlow.setSpacing(0);//设置间距
        mfancyCoverFlow.setMaxRotation(0);//设置最大旋转
        mfancyCoverFlow.setScaleDownGravity(0.5f);
        mfancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
        int num = Integer.MAX_VALUE / 2 % mFancyCoverFlows.size();
        int selectPosition = Integer.MAX_VALUE / 2 - num;
        mfancyCoverFlow.setSelection(selectPosition);
    }
}
