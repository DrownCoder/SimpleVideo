package com.shuyu.video.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuyu.video.R;

/**
 * Created by dengzhaoxuan on 2017/2/17.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>{
    private Context context;

    String[] text;
    int[] icon = new int[]{
            R.mipmap.ic_nav_recommend,
            R.mipmap.ic_clean_cache,
            R.mipmap.ic_service,
            R.mipmap.ic_feedback,
            R.mipmap.ic_update,
            R.mipmap.ic_disclaimer,
            R.mipmap.ic_about
    };
    public UserListAdapter(Context context){
        this.context = context;
        text = new String[]{
                context.getResources().getString(R.string.recommend),
                context.getResources().getString(R.string.clean_cache),
                context.getResources().getString(R.string.service),
                context.getResources().getString(R.string.feedback),
                context.getResources().getString(R.string.update),
                context.getResources().getString(R.string.disclaimer),
                context.getResources().getString(R.string.about)

        };
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                context).inflate(R.layout.user_item_layout, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_id_user.setText(text[position]);
        holder.iv_id_user_icon.setImageResource(icon[position]);
    }
    @Override
    public int getItemCount() {
        return icon.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_id_user_icon;
        TextView tv_id_user;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_id_user_icon = (ImageView) itemView.findViewById(R.id.iv_id_user_icon);
            tv_id_user = (TextView) itemView.findViewById(R.id.tv_id_user);
        }
    }
}
