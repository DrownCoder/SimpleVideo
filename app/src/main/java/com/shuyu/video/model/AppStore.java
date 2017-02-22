package com.shuyu.video.model;

public class AppStore extends DownloadEntity {

    private Long appId;
    private int id;
    private String title;
    private String brief;
    private String summary;
    private String downloadUrl;
    private String iconUrl;
    private String versionCode;
    private String versionName;
    private String packageName;
    private int softwareSize;
    private int appType;
    private String md5;
    private int downloadCount;
    private String imgUrl;
    private int downloadType;
    private int onlineInterval;
    private int notifyCount;
    private int notifyInterval;
    private String adImgUrl;

    public AppStore(Long appId, int id, String title, String brief, String summary,
            String downloadUrl, String iconUrl, String versionCode,
            String versionName, String packageName, int softwareSize, int appType,
            String md5, int downloadCount, String imgUrl, int downloadType,
            int onlineInterval, int notifyCount, int notifyInterval, String adImgUrl) {
        this.appId = appId;
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.summary = summary;
        this.downloadUrl = downloadUrl;
        this.iconUrl = iconUrl;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.packageName = packageName;
        this.softwareSize = softwareSize;
        this.appType = appType;
        this.md5 = md5;
        this.downloadCount = downloadCount;
        this.imgUrl = imgUrl;
        this.downloadType = downloadType;
        this.onlineInterval = onlineInterval;
        this.notifyCount = notifyCount;
        this.notifyInterval = notifyInterval;
        this.adImgUrl = adImgUrl;
    }

    public AppStore() {
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getSoftwareSize() {
        return softwareSize;
    }

    public void setSoftwareSize(int softwareSize) {
        this.softwareSize = softwareSize;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(int downloadType) {
        this.downloadType = downloadType;
    }

    public int getOnlineInterval() {
        return onlineInterval;
    }

    public void setOnlineInterval(int onlineInterval) {
        this.onlineInterval = onlineInterval;
    }

    public int getNotifyCount() {
        return notifyCount;
    }

    public void setNotifyCount(int notifyCount) {
        this.notifyCount = notifyCount;
    }

    public int getNotifyInterval() {
        return notifyInterval;
    }

    public void setNotifyInterval(int notifyInterval) {
        this.notifyInterval = notifyInterval;
    }

    public String getAdImgUrl() {
        return adImgUrl;
    }

    public void setAdImgUrl(String adImgUrl) {
        this.adImgUrl = adImgUrl;
    }
}