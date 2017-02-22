package com.shuyu.video.utils;

import android.os.Build;

import com.sooying.utils.AESUtils;

import java.util.Map;

/**
 * Created by zhangleilei on 9/22/16.
 */

public class DataSignUtils {

    private static final String AES_KEY = "sooyingisy1949@2";

    // 加密
    public static String encryptData(String dataJson) {
        try {
            return AESUtils.getInstance().encryptToBase64Str(dataJson, AES_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 解密
    public static String decryptData(String decodeData) {

        try {
            return AESUtils.getInstance(AES_KEY).decryptFromBase64Str(decodeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //验证签名
    public static String getSign() {
        String sign = CommonUtils.getUUID() +
                CommonUtils.getIMEI() +
                CommonUtils.getIMSI() +
                CommonUtils.getDitchNo() +
                CommonUtils.getAppId() +
                CommonUtils.getVersionCode() +
                MD5Utils.MD5KEY;

        return MD5Utils.md5(sign);
    }

    public static Map<String, String> getEncryptParams() {
        Map<String, String> queryParams = CommonUtils.getCommonParams();
        queryParams.put("sign", getSign());
        queryParams.put("platformType", "1");
        queryParams.put("smsNum", "");
        queryParams.put("screenWidth", ScreenUtils.getScreenWidth() + "");
        queryParams.put("screenHeight", ScreenUtils.getScreenHeight() + "");
        queryParams.put("sdkVersion", String.valueOf(Build.VERSION.SDK_INT));
        queryParams.put("cpuVersion", AppUtils.getCPUInfo());
        queryParams.put("appVersion", String.valueOf(AppUtils.getAppVersion()));
        queryParams.put("thirdChannelId", CommonUtils.getChannelNo());
        queryParams.put("activateFlag", "0");
        return queryParams;
    }
}
