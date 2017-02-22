package com.shuyu.video.model;

import java.util.List;

/**
 * Created by zhangleilei on 9/10/16.
 */

public class LiveVideo {


    private Object totalPageCount;
    private Object pageNo;
    private int totalItemCount;

    private List<VideoPicDetails> nightVideoDetailList;

    public Object getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Object totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Object getPageNo() {
        return pageNo;
    }

    public void setPageNo(Object pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public List<VideoPicDetails> getNightVideoDetailList() {
        return nightVideoDetailList;
    }

    public void setNightVideoDetailList(List<VideoPicDetails> nightVideoDetailList) {
        this.nightVideoDetailList = nightVideoDetailList;
    }

}
