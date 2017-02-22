package com.shuyu.video.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Azure on 2016/9/14.
 */
public class DownloadEntity {

    public static final int NORMAL = -1;
    public static final int PENDING = 0;
    public static final int PROGRESS = 1;
    public static final int ERROR = 2;
    public static final int CONNECTED = 3;
    public static final int PAUSED = 4;
    public static final int COMPLETED = 5;
    public static final int WARN = 6;
    public static final int INSTALLED = 7;

    @IntDef({
            NORMAL, PENDING, PROGRESS, ERROR, CONNECTED, PAUSED, COMPLETED, WARN, INSTALLED
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface DownloadState {
    }

    private int currentSize;
    private int totalSize;
    private int speed;
    private String savePath;

    @DownloadState
    private int downloadState = NORMAL;

    @DownloadState
    public int getDownloadState() {
        return downloadState;
    }

    public void setDownloadState(@DownloadState int downloadState) {
        this.downloadState = downloadState;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
