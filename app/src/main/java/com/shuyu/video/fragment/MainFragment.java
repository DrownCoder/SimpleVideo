package com.shuyu.video.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuyu.video.R;
import com.shuyu.video.api.BaseApi;
import com.shuyu.video.api.IServiceApi;
import com.shuyu.video.customview.TabsView;
import com.shuyu.video.model.ChannelTitle;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {
    private ViewPager viewPager;

    private TabsView mTabs;

    private List<Fragment> fragments;

    private FragmentPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(view);
        initViewSource();
        initEvents();
        return view;
    }

    private void initEvents() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mTabs.setCurrentTab(position, true);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initViewSource() {
        fragments = new ArrayList<>();
        initTabs();
    }

    private void initTabs() {
        BaseApi.request(BaseApi.createApi(IServiceApi.class).getChannelList(),
                new BaseApi.IResponseListener<List<ChannelTitle>>() {
                    @Override
                    public void onSuccess(List<ChannelTitle> data) {
                        String[] tabs = new String[data.size()];
                        for(int i = 0;i<data.size();i++){
                            tabs[i] = data.get(i).getTitle();
                        }
                        //初始化选项卡
                        mTabs.setTabs(tabs);

                        fragments.add(new MovieFragment(data.get(0).getId()));
                        fragments.add(new CollectFragment(data.get(1).getId()));
                        fragments.add(new PhotoFragment(data.get(2).getId()));
                        fragments.add(new PicFragment(data.get(3).getId()));

                        adapter = new FragmentPagerAdapter(getFragmentManager()) {
                            @Override
                            public Fragment getItem(int position) {
                                return fragments.get(position);
                            }

                            @Override
                            public int getCount() {
                                return fragments.size();
                            }
                        };

                        viewPager.setAdapter(adapter);
                    }

                    @Override
                    public void onFail() {

                    }
                });


        mTabs.setOnTabsItemClickListener(new TabsView.OnTabsItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                viewPager.setCurrentItem(position, true);
            }
        });
    }

    private void initViews(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.vp_id_main);
        mTabs = (TabsView) view.findViewById(R.id.tabslayout);
    }

}
