package com.shuyu.video;

import android.app.Application;
import android.util.Base64;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.shuyu.video.api.BasicParamsInterceptor;
import com.shuyu.video.api.CacheInterceptor;
import com.shuyu.video.utils.CommonUtils;
import com.shuyu.video.utils.Constants;
import com.shuyu.video.utils.LogUtils;
import com.shuyu.video.utils.SPUtils;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zhangleilei on 8/31/16.
 */
public class MyApplication extends Application {

    private static MyApplication mApplication;
    private static final int TIMEOUT_READ = 15;
    private static final int TIMEOUT_CONNECTION = 15;
    private static OkHttpClient mOkHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        initImageLoader();
        LogUtils.isDebug = Constants.IS_DEBUG;
        SPUtils.put(this, Constants.STAY_TIME_ON, System.currentTimeMillis());
        LeakCanary.install(this);
    }

    private void initImageLoader() {
        DisplayImageOptions defaultOptions;
        defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .defaultDisplayImageOptions(defaultOptions)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCache(new UnlimitedDiskCache(StorageUtils.getOwnCacheDirectory(this, AppConstants.APP_IMAGE)))
                .diskCacheSize(100 * 1024 * 1024).tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024)
                .threadPoolSize(3)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public OkHttpClient genericClient() {

        if (mOkHttpClient != null)
            return mOkHttpClient;

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level level = BuildConfig.IS_DEBUG ?
                HttpLoggingInterceptor.Level.HEADERS :
                HttpLoggingInterceptor.Level.NONE;
        logInterceptor.setLevel(level);

        Map<String, String> commonParams = CommonUtils.getCommonParams();

        List<String> headerParams = new ArrayList<>();
        headerParams.add("Connection:Keep-Alive");
        headerParams.add("accet:*/*");
        headerParams.add("Accept-Encoding:gzip");
        headerParams.add("User-Agent:okhttp/2.5.0");
        headerParams.add("H-Quality:L");
        headerParams.add("Pay-Key:" + getPayKey(commonParams));

        BasicParamsInterceptor basicParamsInterceptor = new BasicParamsInterceptor.Builder()
                .addHeaderLinesList(headerParams)
                .addQueryParamsMap(commonParams)
                .build();

        File cacheFile = new File(MyApplication.getApplication().getCacheDir(), "retrofit_cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);

        return mOkHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(logInterceptor)
                .addNetworkInterceptor(new CacheInterceptor())
                .addInterceptor(basicParamsInterceptor)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();
    }

    private static String getPayKey(Map<String, String> headerParams) {
        String encodeString = CommonUtils.parseMap(headerParams) + "&payVersion=132";
        byte[] bytes = encodeString.getBytes();
        return new String((Base64.encode(bytes, Base64.DEFAULT))).replace("\n", "");
    }

    public static MyApplication getApplication() {
        return mApplication;
    }
}
