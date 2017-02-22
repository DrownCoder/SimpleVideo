package com.shuyu.video.model;

import java.util.List;

/**
 * Created by zhangleilei on 9/13/16.
 */
public class AppStoreList {

    private int totalPageCount;
    private int pageNo;

    private List<AppStore> appInfoList;

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<AppStore> getAppInfoList() {
        return appInfoList;
    }

    public void setAppInfoList(List<AppStore> appInfoList) {
        this.appInfoList = appInfoList;
    }

}
