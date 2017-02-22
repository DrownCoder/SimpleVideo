package com.shuyu.video.model;

import java.io.Serializable;
import java.util.List;

public class SubChannel implements Serializable {
    private int id;
    private String title;
    private Object imgUrl;

    private List<VideoPicDetails> channelContentList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Object imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<VideoPicDetails> getChannelContentList() {
        return channelContentList;
    }

    public void setChannelContentList(List<VideoPicDetails> channelContentList) {
        this.channelContentList = channelContentList;
    }

    public void updateContentTitle() {
        if (channelContentList == null) return;
        for (int i = 0; i < channelContentList.size(); i++) {
            channelContentList.get(i).setGroupId(id);
            channelContentList.get(i).setGroupTitle(title);
        }
    }

}