package com.shuyu.video.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuyu.video.R;
import com.shuyu.video.api.BaseApi;
import com.shuyu.video.api.IServiceApi;
import com.shuyu.video.fragment.MainFragment;
import com.shuyu.video.fragment.MeFragment;
import com.shuyu.video.fragment.RecommendFragment;
import com.shuyu.video.fragment.VipFragment;
import com.shuyu.video.model.AppStoreList;
import com.shuyu.video.model.ChannelTitle;
import com.shuyu.video.model.LiveVideo;
import com.shuyu.video.model.VideoPicDetails;

import java.util.List;


public class MainActivity extends AppCompatActivity{
    private TextView tvData;

    private FragmentManager manager;

    private FragmentTransaction transaction;

    private MainFragment mainFragment;

    private VipFragment vipFragment;

    private RecommendFragment recommendFragment;

    private MeFragment meFragment;

    private Fragment currentFragment;

    private LinearLayout ll_id_main,ll_id_vip,ll_id_recommend,ll_id_me;

    private ImageView iv_id_main,iv_id_vip,iv_id_recommend,iv_id_me;

    private TextView tv_id_main,tv_id_vip,tv_id_recommend,tv_id_me;

    private List<VideoPicDetails> videolist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        initViews();
        initFragment();
        initDatas();
        initEvents();

        BaseApi.request(BaseApi.createApi(IServiceApi.class).getChannelList(),
                new BaseApi.IResponseListener<List<ChannelTitle>>() {
                    @Override
                    public void onSuccess(List<ChannelTitle> data) {
                        tvData.setText(data.toString());
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    private void initDatas() {
        /**
         * 获取vip数据
         */
        BaseApi.request(BaseApi.createApi(IServiceApi.class).getLiveVideoList(1),
                new BaseApi.IResponseListener<LiveVideo>() {
                    @Override
                    public void onSuccess(LiveVideo data) {
                        videolist = data.getNightVideoDetailList();
                        vipFragment.setData(videolist);
                    }

                    @Override
                    public void onFail() {

                    }
                });
        /**
         * 获取推荐数据
         */
        BaseApi.request(BaseApi.createApi(IServiceApi.class).getAppStoreList(1),
                new BaseApi.IResponseListener<AppStoreList>() {
                    @Override
                    public void onSuccess(AppStoreList data) {
                        recommendFragment.setData(data.getAppInfoList());
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    /**
     * 事件
     */
    private void initEvents() {
        ll_id_main.setOnClickListener(onClickListener);
        ll_id_vip.setOnClickListener(onClickListener);
        ll_id_recommend.setOnClickListener(onClickListener);
        ll_id_me.setOnClickListener(onClickListener);
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        mainFragment = new MainFragment();
        vipFragment = new VipFragment();
        recommendFragment = new RecommendFragment();
        meFragment = new MeFragment();

        transaction.add(R.id.fl_id_fragmentframe,mainFragment);
        transaction.commit();

        setUIClicked(iv_id_main,tv_id_main);
        currentFragment = mainFragment;
    }

    private void initViews() {
        ll_id_main = (LinearLayout) findViewById(R.id.ll_id_main);
        ll_id_vip = (LinearLayout) findViewById(R.id.ll_id_vip);
        ll_id_recommend = (LinearLayout) findViewById(R.id.ll_id_recommend);
        ll_id_me = (LinearLayout) findViewById(R.id.ll_id_me);

        iv_id_main = (ImageView) findViewById(R.id.iv_id_main);
        iv_id_vip = (ImageView) findViewById(R.id.iv_id_vip);
        iv_id_recommend = (ImageView) findViewById(R.id.iv_id_recommend);
        iv_id_me = (ImageView) findViewById(R.id.iv_id_me);

        tv_id_main = (TextView) findViewById(R.id.tv_id_main);
        tv_id_vip = (TextView) findViewById(R.id.tv_id_vip);
        tv_id_recommend = (TextView) findViewById(R.id.tv_id_recommend);
        tv_id_me = (TextView) findViewById(R.id.tv_id_me);

    }

    /**
     * 使用show() hide()切换页面
     * 显示fragment
     */
    private void showFragment(Fragment fg){
        transaction = manager.beginTransaction();
        //如果之前没有添加过
        if(!fg.isAdded()){
            transaction
                    .hide(currentFragment)
                    .add(R.id.fl_id_fragmentframe,fg);
        }else{
            transaction
                    .hide(currentFragment)
                    .show(fg);
        }

        //全局变量，记录当前显示的fragment
        currentFragment = fg;

        transaction.commit();

    }

    /**
     * 隐藏fragment
     * @param fg
     */
    private void hideFragment(Fragment fg){
        transaction = manager.beginTransaction();

        //如果之前没有添加过
        if(!fg.isAdded()){
            transaction
                    .add(R.id.fl_id_fragmentframe,fg)
                    .hide(fg);

        }else{
            transaction
                    .hide(fg);
        }

        transaction.commit();
    }

    /**
     * 重置底部栏
     */
    private void resetUI(){
        iv_id_main.setImageResource(R.mipmap.ic_nav_main);
        iv_id_vip.setImageResource(R.mipmap.ic_nav_vip);
        iv_id_recommend.setImageResource(R.mipmap.ic_nav_recommend);
        iv_id_me.setImageResource(R.mipmap.ic_nav_user);

        tv_id_main.setTextColor(getResources().getColor(R.color.black_1));
        tv_id_vip.setTextColor(getResources().getColor(R.color.black_1));
        tv_id_recommend.setTextColor(getResources().getColor(R.color.black_1));
        tv_id_me.setTextColor(getResources().getColor(R.color.black_1));
    }

    /**
     * 底部栏设置点击效果
     * @param iv
     * @param tv
     */
    private void setUIClicked(ImageView iv,TextView tv){
        tv.setTextColor(getResources().getColor(R.color.app_main_color_pressed));
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_id_main:
                    resetUI();
                    setUIClicked(iv_id_main,tv_id_main);
                    showFragment(mainFragment);
                    hideFragment(recommendFragment);
                    hideFragment(vipFragment);
                    hideFragment(meFragment);
                    break;
                case R.id.ll_id_vip:
                    resetUI();
                    setUIClicked(iv_id_vip,tv_id_vip);
                    showFragment(vipFragment);
                    hideFragment(recommendFragment);
                    hideFragment(mainFragment);
                    hideFragment(meFragment);
                    break;
                case R.id.ll_id_recommend:
                    resetUI();
                    setUIClicked(iv_id_recommend,tv_id_recommend);
                    showFragment(recommendFragment);
                    hideFragment(mainFragment);
                    hideFragment(vipFragment);
                    hideFragment(meFragment);
                    break;
                case R.id.ll_id_me:
                    resetUI();
                    setUIClicked(iv_id_me,tv_id_me);
                    showFragment(meFragment);
                    hideFragment(recommendFragment);
                    hideFragment(vipFragment);
                    hideFragment(mainFragment);
                    break;
            }
        }
    };
}
