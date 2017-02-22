package com.shuyu.video.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shuyu.video.R;
import com.shuyu.video.model.AppStore;

import java.util.List;

/**
 * Created by dengzhaoxuan on 2017/2/17.
 */

public class RecomAdapter extends RecyclerView.Adapter<RecomAdapter.ViewHolder>{
    private Context context;
    private List<AppStore> data;
    public RecomAdapter(Context context ,List<AppStore> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                context).inflate(R.layout.recom__item_layout, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppStore item = data.get(position);
        if(item != null){
            holder.tv_id_title.setText(item.getTitle());
            ImageLoader.getInstance().displayImage(item.getIconUrl(),holder.iv_id_recom_icon);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_id_recom_icon;
        TextView tv_id_title;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_id_recom_icon = (ImageView) itemView.findViewById(R.id.iv_id_recom_icon);
            tv_id_title = (TextView) itemView.findViewById(R.id.tv_id_title);
        }
    }
}
