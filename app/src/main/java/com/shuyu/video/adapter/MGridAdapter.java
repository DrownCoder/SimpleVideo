package com.shuyu.video.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shuyu.video.R;
import com.shuyu.video.model.SubChannel;
import com.shuyu.video.model.VideoPicDetails;

import java.util.List;

/**
 * Created by dengzhaoxuan on 2017/2/15.
 */

public class MGridAdapter extends BaseAdapter{
    private List<VideoPicDetails> datas;
    private LayoutInflater inflater;

    public MGridAdapter(Context context, List<VideoPicDetails> datas){
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }
    public void setDatas(List<VideoPicDetails> datas){
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        VideoPicDetails item = datas.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.card_item, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder=(ViewHolder) convertView.getTag();
        }
        if(item.isFromLocal()){
            ImageLoader.getInstance().displayImage("file://"+item.getImgUrl(),holder.iv_id_cardpic);
        }else{
            ImageLoader.getInstance().displayImage(item.getImgUrl(),holder.iv_id_cardpic);
        }
        holder.card_note.setText(item.getSubTitle());
        holder.online.setText(item.getViewNumber());
        holder.card_title.setText(item.getTitle());
        return convertView;
    }
    class ViewHolder{
        private TextView card_title;
        private TextView online;
        private TextView card_note;
        private ImageView iv_id_cardpic;

        public ViewHolder(View itemView) {
            iv_id_cardpic = (ImageView) itemView.findViewById(R.id.iv_id_cardpic);
            card_title = (TextView) itemView.findViewById(R.id.tv_id_title);
            online = (TextView) itemView.findViewById(R.id.tv_id_online);
            card_note = (TextView) itemView.findViewById(R.id.tv_id_cardnote);
        }
    }
}
