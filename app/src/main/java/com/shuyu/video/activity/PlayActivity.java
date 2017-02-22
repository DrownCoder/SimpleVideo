package com.shuyu.video.activity;

import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.shuyu.video.R;
import com.shuyu.video.adapter.CommentAdapter;
import com.shuyu.video.api.BaseApi;
import com.shuyu.video.api.IServiceApi;
import com.shuyu.video.customview.SelfDialog;
import com.shuyu.video.model.VideoComment;
import com.shuyu.video.model.VideoPicDetails;
import com.shuyu.video.service.VideoService;
import com.shuyu.video.utils.DateUtils;

import java.io.IOException;
import java.util.List;

public class PlayActivity extends AppCompatActivity{
    private ImageView iv_id_back;
    private TextView tv_id_titile, tv_id_long, tv_id_title_main, tv_id_timelong_bottom, tv_id_online, tv_id_comment;
    private String time;

    private RelativeLayout rl_id_playui;
    private ProgressBar pb_id_video;
    private TextView tv_id_currenttime;
    private ImageView iv_id_control;
    private SeekBar sb_progress;

    private SurfaceView sv_id_video;
    private MediaPlayer player;
    private VideoPicDetails details;
    private SurfaceHolder holder;
    private int position = 0;
    private boolean isSurfaceCreated = false;

    private RecyclerView rcl_id_hotcomment;
    private CommentAdapter adapter;

    private static final int MSG_UPDATE = 0x120;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == MSG_UPDATE){
                handler.postDelayed(runnable, 1000);
            }
        }
    };

    Runnable runnable=new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if(sb_progress.getProgress()>=sb_progress.getMax()){
                // playmusicbu.setImageResource(R.mipmap.pause);
                //Toast.makeText(MainActivity.this,"下一首",Toast.LENGTH_SHORT).show();
                //cycleViewPager.setCurrentItem(++mCurrentIndex);
                sb_progress.setProgress(0);
            }else{
                //Log.i("run","run");
                int progress = sb_progress.getProgress();
                sb_progress.setProgress(progress + 1);
                handler.postDelayed(runnable, 1000);
                tv_id_currenttime.setText(DateUtils.formatTimeForVideoLong(progress));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent = getIntent();
        details = (VideoPicDetails) intent.getSerializableExtra("video");

        initViews();
        initViewSource();
        initEvents();

    }

    private void initEvents() {
        iv_id_back.setOnClickListener(onClickListener);
        tv_id_comment.setOnClickListener(onClickListener);
        sv_id_video.setOnClickListener(onClickListener);
        iv_id_control.setOnClickListener(onClickListener);

        sb_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                pb_id_video.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                player.seekTo(progress*1000);
                player.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                    @Override
                    public void onSeekComplete(MediaPlayer mp) {
                        pb_id_video.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }

    private void initViewSource() {
        tv_id_titile.setText(details.getTitle());
        time = DateUtils.formatTimeForVideoLong(details.getVideoLength());
        tv_id_long.setText(time);
        tv_id_title_main.setText(details.getTitle());
        tv_id_timelong_bottom.setText(time);
        tv_id_online.setText(details.getViewNumber());

        /**
         * 列表数据
         */
        BaseApi.request(BaseApi.createApi(IServiceApi.class).getVideoCommentList(),
                new BaseApi.IResponseListener<List<VideoComment>>() {
                    @Override
                    public void onSuccess(List<VideoComment> data) {
                        adapter = new CommentAdapter(PlayActivity.this, data);
                        rcl_id_hotcomment.setLayoutManager(new LinearLayoutManager(PlayActivity.this));
                        rcl_id_hotcomment.setAdapter(adapter);
                    }

                    @Override
                    public void onFail() {

                    }
                });
        /**
         * 播放视频准备
         */
        player = new MediaPlayer();
        rl_id_playui.setClickable(false);
        CreateSurface();
        //sv_id_video.getHolder().addCallback(new MyCallBack());
        //sv_id_video.getHolder().setKeepScreenOn();
       /* try {
            player.reset();
            player.setDataSource(this, Uri.parse(details.getVideoUrl()));
            holder=sv_id_video.getHolder();
            holder.addCallback(new MyCallBack());
            //异步准备 准备工作在子线程中进行 当播放网络视频时候一般采用此方法
            player.prepareAsync();
            player.setLooping(false);
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //progressBar.setVisibility(View.INVISIBLE);
                    pb_id_video.setVisibility(View.GONE);
                    rl_id_playui.setClickable(true);
                    player.start();
                    if(position>0) player.seekTo(position);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /**
         * 显示3s播放UI
         */
        showVideoUi();
    }
/*    @Override
    protected void onDestroy() {
        player.release();
        player = null;
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        player.pause();
        super.onStop();
    }*/

    /*   private void play(int position) {
           try {
               player.reset();
               player.setDataSource(this, Uri.parse(details.getVideoUrl()));
               player.setDisplay(sv_id_video.getHolder());
               player.prepareAsync();//缓冲
               player.setOnPreparedListener(new PrepareListener(position));
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       private final class PrepareListener implements MediaPlayer.OnPreparedListener {
           private int position;

           public PrepareListener(int position) {
               this.position = position;
           }

           public void onPrepared(MediaPlayer mp) {
               pb_id_video.setVisibility(View.GONE);
               rl_id_playui.setClickable(true);
               player.start();
               if(position>0) player.seekTo(position);
           }
       }*/
    private void showVideoUi() {
        rl_id_playui.setVisibility(View.VISIBLE);
        /**
         * 显示3秒播放UI
         */
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rl_id_playui.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }

    private void initViews() {
        iv_id_back = (ImageView) findViewById(R.id.iv_id_back);
        tv_id_titile = (TextView) findViewById(R.id.tv_id_titile);
        tv_id_long = (TextView) findViewById(R.id.tv_id_long);
        tv_id_title_main = (TextView) findViewById(R.id.tv_id_title_main);
        tv_id_timelong_bottom = (TextView) findViewById(R.id.tv_id_timelong_bottom);
        tv_id_online = (TextView) findViewById(R.id.tv_id_online);
        tv_id_comment = (TextView) findViewById(R.id.tv_id_comment);
        sv_id_video = (SurfaceView) findViewById(R.id.sv_id_video);
        pb_id_video = (ProgressBar) findViewById(R.id.pb_id_video);

        rl_id_playui = (RelativeLayout) findViewById(R.id.rl_id_playui);
        iv_id_control = (ImageView) findViewById(R.id.iv_id_control);
        tv_id_currenttime = (TextView) findViewById(R.id.tv_id_currenttime);
        sb_progress = (SeekBar) findViewById(R.id.sb_progress);

        rcl_id_hotcomment = (RecyclerView) findViewById(R.id.rcl_id_hotcomment);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_id_back:
                    finish();
                    break;
                case R.id.tv_id_comment:
                    final SelfDialog dialog = new SelfDialog(PlayActivity.this);
                    dialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            String comment = dialog.getComment();
                            VideoComment videoComment = new VideoComment();
                            videoComment.setContent(comment);
                            adapter.addItem(videoComment);
                            dialog.dismiss();
                        }
                    });
                    dialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    break;
                case R.id.sv_id_video:
                    showVideoUi();
                    break;
                case R.id.iv_id_control:
                    if (player.isPlaying()) {
                        iv_id_control.setImageResource(R.mipmap.play);
                        position = player.getCurrentPosition();
                        player.pause();
                        handler.removeCallbacks(runnable);
                    } else {
                        iv_id_control.setImageResource(R.mipmap.pause);
                        player.start();
                        handler.sendEmptyMessage(MSG_UPDATE);
                        //Play(position);
                    }
                    break;
            }
        }
    };

  /*  private class MyCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            //player.setDisplay(holder);
            play(position);
            position = 0;
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if(player.isPlaying()){
                position = player.getCurrentPosition();
                player.stop();
            }
        }
    }
*/


    /**
     * 创建视频展示页面
     */
    private void CreateSurface() {
        holder = sv_id_video.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); //兼容4.0以下的版本
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

                isSurfaceCreated = false;
                if (player.isPlaying())//此处需要注意
                {
                    position = player.getCurrentPosition();
                    player.pause();
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                isSurfaceCreated = true;
                player.setDisplay(holder);//页面创建好了以后再展示
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {

            }
        });

    }

    /**
     * 释放播放器资源
     */
    private void ReleasePlayer() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }

    }

    /**
     * 暂停
     */
    private void Pause() {
        if (player != null && player.isPlaying()) {
            position = player.getCurrentPosition();
            player.pause();

        }

    }


    private void Play(final int currentPosition) {
        if(position == 0){
            try {
                player.reset();
                player.setDataSource(this, Uri.parse(details.getVideoUrl()));
                player.setDisplay(sv_id_video.getHolder());
                player.setLooping(true);

                player.prepareAsync();
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        pb_id_video.setVisibility(View.INVISIBLE);
                        rl_id_playui.setClickable(true);
                        player.seekTo(currentPosition);
                        player.start();
                        sb_progress.setMax(DateUtils.totalSeconds("00:00:00", time));
                        sb_progress.setProgress(position/1000);
                        handler.postDelayed(runnable, 1000);
                    }
                });

                player.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {

                        return false;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            iv_id_control.setImageResource(R.mipmap.pause);
            player.start();
        }
    }

    /**
     * 创建完毕页面后需要将播放操作延迟10ms防止因surface创建不及时导致播放失败
     */
    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (isSurfaceCreated) {
                    Play(position);
                }
            }
        }, 10);

    }

    /**
     * 页面从前台到后台会执行 onPause ->onStop 此时Surface会被销毁，
     * 再一次从后台 到前台时需要 重新创建Surface
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        if (!isSurfaceCreated) {
            CreateSurface();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Pause();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ReleasePlayer();

    }
}
