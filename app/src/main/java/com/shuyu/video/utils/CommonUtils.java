package com.shuyu.video.utils;

import android.os.Build;

import com.shuyu.video.BuildConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Azure on 2016/9/22.
 */

public class CommonUtils {

    public static String getIMSI() {
        return AppUtils.getIMSI();
    }

    public static String getIMEI() {
        return AppUtils.getIMEI();
    }

    public static String getManufacturer() {
        return Build.BRAND;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static String getVersionCode() {
        return String.valueOf(AppUtils.getAppVersion());
    }

    public static int getAppId() {
        return BuildConfig.APP_ID;
    }

    public static String getDcVersion() {
        return "000002";
    }

    public static String getDitchNo() {
        return BuildConfig.ditchNo;
    }

    public static String getChannelNo() {
        return BuildConfig.FLAVOR;
    }

    public static String getUUID() {
        return AppUtils.getUUID();
    }

    public static Map<String, String> getCommonParams() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("imsi", getIMSI());
        queryParams.put("imei", getIMEI());
        queryParams.put("manufacturer", getManufacturer());
        queryParams.put("model", getModel());
        queryParams.put("versionCode", getVersionCode());
        queryParams.put("appId", String.valueOf(getAppId()));
        queryParams.put("dcVersion", getDcVersion());
        queryParams.put("ditchNo", getDitchNo());
        queryParams.put("uuid", getUUID());
        return queryParams;
    }

    public static String parseMap(Map<String, String> headerParams) {
        StringBuilder encodeString = new StringBuilder();
        for (Map.Entry entry : headerParams.entrySet()) {
            encodeString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return encodeString.toString().substring(0, encodeString.toString().lastIndexOf("&"));
    }

}
