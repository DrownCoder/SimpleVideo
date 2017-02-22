package com.shuyu.video.model;

import java.util.List;

/**
 * Created by Azure on 2016/8/31.
 */

public class ChannelVideo {

    private int totalPageCount;
    private int totalItemCount;
    private int pageNo;

    private List<SubChannel> videoChannelList;

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

    public List<SubChannel> getVideoChannelList() {
        return videoChannelList;
    }

    public void setVideoChannelList(List<SubChannel> videoChannelList) {
        this.videoChannelList = videoChannelList;
    }


}
