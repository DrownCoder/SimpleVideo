package com.shuyu.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.dalong.francyconverflow.FancyCoverFlow;
import com.dalong.francyconverflow.FancyCoverFlowAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shuyu.video.R;
import com.shuyu.video.model.VideoPicDetails;

import java.util.List;


public class MyFancyCoverFlowAdapter extends FancyCoverFlowAdapter {
    private Context mContext;

    public List<VideoPicDetails> list;

    public MyFancyCoverFlowAdapter(Context context, List<VideoPicDetails> list) {
        mContext = context;
        this.list = list;
    }

    @Override
    public View getCoverFlowItem(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fancycoverflow, null);
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            convertView.setLayoutParams(new FancyCoverFlow.LayoutParams((int)((float)width / 1.5f), FancyCoverFlow.LayoutParams.WRAP_CONTENT));
            holder = new ViewHolder();
            holder.tv_id_vip = (TextView) convertView.findViewById(R.id.tv_id_vip);
            holder.iv_id_vip = (ImageView) convertView.findViewById(R.id.iv_id_vip);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        VideoPicDetails item = getItem(position);
        holder.tv_id_vip.setText(item.getTitle());
        ImageLoader.getInstance().displayImage(item.getImgUrl(),holder.iv_id_vip);
        /*holder.product_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });*/
        return convertView;
    }

    /*public void setSelected(int position) {
        position = position % list.size();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (i == position) {
                    list.get(i).setSelected(true);
                } else {
                    list.get(i).setSelected(false);
                }
            }
        }
        notifyDataSetChanged();

    }*/

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public VideoPicDetails getItem(int i) {
        return list.get(i % list.size());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    static class ViewHolder {
        TextView tv_id_vip;
        ImageView iv_id_vip;
    }
}