package com.shuyu.video.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.shuyu.video.R;
import com.shuyu.video.activity.PlayActivity;
import com.shuyu.video.customview.MGridView;
import com.shuyu.video.model.CardItem;
import com.shuyu.video.model.ChannelVideo;
import com.shuyu.video.model.SubChannel;
import com.shuyu.video.model.VideoPicDetails;

import java.util.List;

/**
 * Created by dengzhaoxuan on 2017/2/15.
 */

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.MViewHolder>{
    private Context context;
    private ChannelVideo datas;
    private MGridAdapter mGridAdapter;
    private List<VideoPicDetails> itemList;
    private AdapterView.OnItemClickListener onItemClickListener;
    public CardListAdapter(final Context context, ChannelVideo datas) {
        this.context = context;
        this.datas = datas;
        mGridAdapter = new MGridAdapter(context,itemList);
        onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, PlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("video",itemList.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        };
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MViewHolder holder = new MViewHolder(LayoutInflater.from(
                context).inflate(R.layout.cardlist_item_layout, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
        itemList = getItemCardData(position);
        if(datas != null){
            mGridAdapter.setDatas(itemList);
            //holder.mgv_id_grid.setAdapter(new MGridAdapter(context,itemList));
            holder.mgv_id_grid.setAdapter(mGridAdapter);
            holder.mgv_id_grid.setOnItemClickListener(onItemClickListener);
            SubChannel item = datas.getVideoChannelList().get(position);
            holder.card_title.setText(item.getTitle());
        }
    }

    /**
     * 获取单个大卡片的数据
     * @param position
     */
    private List<VideoPicDetails> getItemCardData(int position) {
        return  datas.getVideoChannelList().get(position).getChannelContentList();
    }

    @Override
    public int getItemCount() {
        if(datas != null)
        return  datas.getVideoChannelList().size();

        return 0;
    }

    class MViewHolder extends RecyclerView.ViewHolder{
        private TextView card_title;
        private MGridView mgv_id_grid;

        public MViewHolder(View view) {
            super(view);
            card_title = (TextView) view.findViewById(R.id.tv_id_cardtitle);


            //lgv_id_gridview = (ListGridView<CardItem>) itemView.findViewById(R.id.lgv_id_gridview);
            mgv_id_grid = (MGridView) itemView.findViewById(R.id.mgv_id_grid);
        }
    }
}
