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
import com.shuyu.video.model.VideoComment;

import java.util.List;

/**
 * Created by dengzhaoxuan on 2017/2/16.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Viewholder>{
    private List<VideoComment> data;
    private Context context;
    public CommentAdapter(Context context, List<VideoComment> data){
        this.data = data;
        this.context = context;
    }
    public void addItem(VideoComment item){
        data.add(item);
        notifyDataSetChanged();
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        Viewholder holder = new Viewholder(LayoutInflater.from(
                context).inflate(R.layout.comment_item_layout, parent,
                false));
        return holder;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        VideoComment item = data.get(position);
        holder.tv_id_comment.setText(item.getContent());
        ImageLoader.getInstance().displayImage(item.getHeadImgUrl(),holder.iv_id_peo);
    }

    class Viewholder extends RecyclerView.ViewHolder{
        TextView tv_id_comment;
        ImageView iv_id_peo;
        public Viewholder(View itemView) {
            super(itemView);
            tv_id_comment = (TextView) itemView.findViewById(R.id.tv_id_comment);
            iv_id_peo = (ImageView) itemView.findViewById(R.id.iv_id_peo);

        }
    }
}
