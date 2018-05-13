package com.example.pc.hanh3;


import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtTitle, txtTimeSong, txtTimetotal;
    SeekBar sksong;
    ImageButton btnPrev, btnPlay,  btnNext;
    ArrayList<? extends Song> arraySong ;
    ImageView imgDisc;
    MediaPlayer mediaPlayer;
    int pos = 0;
    ObjectAnimator anim;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        AnhXa();
        toolbar.setTitle(R.string.app_name);
        KhoiTaoAnim();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pos = bundle.getInt("KEY");
            arraySong = (ArrayList<? extends Song>) bundle.getSerializable("LIST_SONG");
        }

        KhoiTaoMediaPlayer();

        Controllers();

    }

    private void KhoiTaoAnim() {
        anim = ObjectAnimator.ofFloat(imgDisc, "rotation", 0, 360);
        anim.setDuration(10000);
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.setRepeatMode(ObjectAnimator.RESTART);
        anim.setInterpolator(new LinearInterpolator());
    }


    private void KhoiTaoMediaPlayer() {
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(pos).getFile());
        txtTitle.setText(arraySong.get(pos).getTitle());
        imgDisc.setImageResource(arraySong.get(pos).getImage());
        btnPlay.setImageResource(R.drawable.icon_pause);
        mediaPlayer.start();
        setTimeTotal();
        UpdateTimeCurrent();
        anim.start();
    }

    private void Controllers() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.icon_play);
                    anim.pause();
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.icon_pause);
                    anim.resume();
                }
                setTimeTotal();
                UpdateTimeCurrent();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = (pos + 1) % arraySong.size();
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                KhoiTaoMediaPlayer();

            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = (pos + arraySong.size() - 1) % arraySong.size();
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                KhoiTaoMediaPlayer();

            }

        });

        sksong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sksong.getProgress());
            }
        });
    }
    private void UpdateTimeCurrent(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(timeFormat.format(mediaPlayer.getCurrentPosition()));
                sksong.setProgress(mediaPlayer.getCurrentPosition());

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer player) {
                        pos = (pos + 1) % arraySong.size();
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                        }
                        KhoiTaoMediaPlayer();
                    }
                });

                handler.postDelayed(this, 1000);
            }
        }, 100);
    }
    private void setTimeTotal(){
        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
        txtTimetotal.setText(timeFormat.format(mediaPlayer.getDuration()));
        sksong.setMax(mediaPlayer.getDuration());
    }



    private void AnhXa() {
        txtTimeSong= (TextView) findViewById(R.id.timeStart);
        txtTimetotal= (TextView) findViewById(R.id.TimeTotal);
        txtTitle= (TextView) findViewById(R.id.tenbaihat);
        sksong = (SeekBar) findViewById(R.id.seekBar);
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        imgDisc = (ImageView) findViewById(R.id.img_disc);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        Log.d("DESTROY", "onDestroy: ");
    }

}
