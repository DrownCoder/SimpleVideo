package com.shuyu.video.activity;

import android.app.Activity;

/**
 * Created by dengzhaoxuan on 2017/2/22.
 */

public abstract class BaseActivity extends Activity{
    public abstract void initViews();
    public abstract void initDatas();
    public abstract void initEvents();

}
