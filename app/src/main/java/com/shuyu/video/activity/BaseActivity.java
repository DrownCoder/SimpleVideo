package com.shuyu.video.activity;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by dengzhaoxuan on 2017/2/22.
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 初始化组件
     */
    public abstract void initViews();

    /**
     * 初始化数据
     */
    public abstract void initDatas();

    /**
     * 初始化事件
     */
    public abstract void initEvents();
}
