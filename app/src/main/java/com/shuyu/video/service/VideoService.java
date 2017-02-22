package com.shuyu.video.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.view.SurfaceHolder;
import android.widget.SeekBar;

public class VideoService extends Service {
    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer;
    public MyBinder myBinder;
    public static SeekBar progressBar;

    String url;

    //
    @Override
    public IBinder onBind(Intent intent) {
// TODO Auto-generated method stub
        return myBinder;
    }


    // 创建
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        myBinder = new MyBinder();


    }


    // 开始
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
// TODO Auto-generated method stub
        return super.onStartCommand(intent, flags, startId);
    }


    // 销毁
    @Override
    public void onDestroy() {
// TODO Auto-generated method stub
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }


    public class MyBinder extends Binder {
        // 播放
        public void play(SurfaceHolder surfaceHolder) {
            VideoService.this.play(surfaceHolder);
        }


        // 停止
        public void cease() {
            VideoService.this.cease();
        }


        // 重播
        public void reset(SurfaceHolder surfaceHolder) {
            VideoService.this.reset(surfaceHolder);
        }


        // 暂停
        public void suspend() {
            VideoService.this.suspend();
        }
    }


    // 停止
    public void cease() {
// 停止
        mediaPlayer.stop();
// 释放资源
        mediaPlayer.release();
    }


    // 重播
    public void reset(SurfaceHolder surfaceHolder) {
// 停止
        mediaPlayer.stop();
// 调播放方法
        play(surfaceHolder);
    }


    // 暂停
    public void suspend() {
// 判断是否是播放状态
        if (mediaPlayer.isPlaying()) {
// 如果是播放状态就暂停
            mediaPlayer.pause();
        } else {
// 如果不是播放状态就播放
            mediaPlayer.start();
// 调进程刷新进度条
            new Thread(run).start();
        }


    }


    // 播放
    public void play(SurfaceHolder surfaceHolder) {
// 重置
        mediaPlayer.reset();
// 文件路径
        //file = new File(Environment.getExternalStorageDirectory()
        //        + "/video.mp4");
// 准备
        try {
// 数据路径
            mediaPlayer.setDataSource("path");
// 展示
            mediaPlayer.setDisplay(surfaceHolder);
// 准备
            mediaPlayer.prepare();
// 调进程刷新进度条
            new Thread(run).start();
// 进度条的改变事件来设置播放进度
            progressBar.setOnSeekBarChangeListener(new myjindu());


            mediaPlayer.setOnPreparedListener(new mypreparedListener());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //
    class mypreparedListener implements MediaPlayer.OnPreparedListener {


        @Override
        public void onPrepared(MediaPlayer mp) {
// TODO Auto-generated method stub
            mediaPlayer.start();
        }


    }


    // 设置进度
    public class myjindu implements SeekBar.OnSeekBarChangeListener {


        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
// TODO Auto-generated method stub
            if (fromUser) {
                System.out.println(progress + "---" + fromUser);
// 设置播放进度
                mediaPlayer.seekTo(progress);
            }
        }


        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }


        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }


    }


    //
    Handler progressHandler = new Handler();
    Runnable run = new Runnable() {


        public void run() {
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
            }
// 设置最大值
            int max = mediaPlayer.getDuration();
            System.out.println(max);
            progressBar.setMax(max);
// 设置进度条
            int currentPosition = mediaPlayer.getCurrentPosition();
            System.out.println(currentPosition);
            progressBar.setProgress(currentPosition);
// 每隔100 milliseconds更新进度条
            if (mediaPlayer.isPlaying()) {
                progressHandler.postDelayed(run, 100);
            }
        }
    };
}