package com.shuyu.video.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuyu.video.R;
import com.shuyu.video.utils.DensityUtils;

/**
 * 一个简单的Tabs选项卡视图
 *
 */
public class TabsView extends LinearLayout {

    private int mSelectedColor = 0xffff0000;// 选中的字体颜色
    private int mNotSelectedColor = 0x00000000;// 未选中的字体颜色

    private int mIndicatorColor = 0xff0000ff;// 指示器的颜色

    private LinearLayout mTabsContainer;// 放置tab的容器
    private View mIndicator, mBottomLine;// 指示器和底部横线

    private int mTabTextSize = 18;//Tab字体的大小

    private int mTabHeight = 8;//Tab导航条的高度

    private OnTabsItemClickListener listener;

    public TabsView(Context context) {
        this(context, null);
    }

    public TabsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TabsView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.TabsView_tab_textsize:
                    mTabTextSize = a.getDimensionPixelSize(attr, DensityUtils.sp2px(getContext(),16));
                    break;
                case R.styleable.TabsView_tab_color:
                    // 指示器颜色
                    mIndicatorColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.TabsView_tab_textcolor_clicked:
                    // 选中Tab字体的颜色
                    mSelectedColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.TabsView_tab_height:
                    //tab导航条的高度
                    mTabHeight = a.getDimensionPixelSize(attr,DensityUtils.dp2px(getContext(),2));
                    break;
                case R.styleable.TabsView_tab_textcolor:
                    mNotSelectedColor = a.getColor(attr,Color.BLACK);
                    break;
            }

        }
        a.recycle();




        setOrientation(VERTICAL);
        // 初始化容器
        mTabsContainer = new LinearLayout(getContext());
        mTabsContainer.setOrientation(HORIZONTAL);
        mTabsContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        addView(mTabsContainer);
        // 初始化指示器
        mIndicator = new View(getContext());
        mIndicator.setBackgroundColor(mIndicatorColor);
        mIndicator.setLayoutParams(new LayoutParams(300, mTabHeight));// 先任意设置宽度
        addView(mIndicator);
        // 初始化底部横线
        mBottomLine = new View(getContext());
        mBottomLine.setBackgroundColor(mIndicatorColor);
        mBottomLine.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 2));
        addView(mBottomLine);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resetIndicator();
    }

    /**
     * 重新设置指示器
     */
    private void resetIndicator() {
        int childCount = mTabsContainer.getChildCount();
        ViewGroup.LayoutParams layoutParams = mIndicator.getLayoutParams();
        if (childCount <= 0) {
            layoutParams.width = 0;
        } else {
            layoutParams.width = getWidth() / childCount;
        }
        mIndicator.setLayoutParams(layoutParams);
        // mIndicator.setX(0f);
    }

    /**
     * 设置选项卡
     * 
     * @param titles
     */
    public void setTabs(String... titles) {
        mTabsContainer.removeAllViews();
        if (titles != null) {
            for (int i = 0; i < titles.length; i++) {
                TextView textView = new TextView(getContext());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTabTextSize);
                textView.setTextColor(mNotSelectedColor);
                textView.setText(titles[i]);
                textView.setClickable(true);
                textView.setPadding(0, 10, 0, 10);
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
                textView.setTag(i);
                textView.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int position = (Integer) v.getTag();
                        setCurrentTab(position, true);
                        if (listener != null) {
                            listener.onClick(v, position);
                        }
                    }
                });
                mTabsContainer.addView(textView);
            }
            // 初始化，默认选中第一个
            setCurrentTab(0, false);
            // 设置指示器
            post(new Runnable() {
                @Override
                public void run() {
                    // 设置指示器
                    resetIndicator();
                }
            });
        }
    }

    /**
     * 设置当前的tab
     * 
     * @param position
     */
    public void setCurrentTab(int position, boolean anim) {
        int childCount = mTabsContainer.getChildCount();
        if (position < 0 || position >= childCount) {
            return;
        }
        // 设置每个tab的状态
        for (int i = 0; i < childCount; i++) {
            TextView childView = (TextView) mTabsContainer.getChildAt(i);
            if (i == position) {
                childView.setTextColor(mSelectedColor);
            } else {
                childView.setTextColor(mNotSelectedColor);
            }
        }
        // 指示器的移动
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mIndicator, "x", position * mIndicator.getWidth());
        if (anim) {
            objectAnimator.setDuration(200).start();
        } else {
            objectAnimator.setDuration(0).start();
        }
    }

    /**
     * Tabs点击的监听事件
     * 
     * @param listener
     */
    public void setOnTabsItemClickListener(OnTabsItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnTabsItemClickListener {
        public void onClick(View view, int position);
    }
}