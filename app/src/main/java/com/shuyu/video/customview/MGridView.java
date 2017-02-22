package com.shuyu.video.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by dengzhaoxuan on 2017/2/15.
 */

public class MGridView extends GridView{
    public MGridView(Context context) {
        this(context,null);
    }

    public MGridView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public int getNumColumns() {
        return 2;
    }
}
