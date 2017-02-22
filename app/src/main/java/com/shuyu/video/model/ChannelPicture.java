package com.shuyu.video.model;

import java.util.List;

/**
 * Created by Azure on 2016/8/31.
 */
public class ChannelPicture {

    private int totalPageCount;
    private int totalItemCount;
    private int pageNo;

    private List<SubChannel> picChannelList;

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<SubChannel> getPicChannelList() {
        return picChannelList;
    }

    public void setPicChannelList(List<SubChannel> picChannelList) {
        this.picChannelList = picChannelList;
    }
}
