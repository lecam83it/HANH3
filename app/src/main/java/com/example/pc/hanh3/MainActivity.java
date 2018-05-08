package com.example.pc.hanh3;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtTitle, txtTimeSong, txtTimetotal;
    SeekBar sksong;
    ImageButton btnPrev, btnPlay,  btnNext;
    ArrayList<Song> arraySong;

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        AddSong();

        Controllers();

    }

    private void Controllers() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.co_gai_m52);
                mediaPlayer.start();
            }
        });
    }

    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Cô Gái M52", R.raw.co_gai_m52));
        arraySong.add(new Song("Đừng Như Thói Quen", R.raw.dung_nhu_thoi_quen));
    }

    private void AnhXa() {
        txtTimeSong= (TextView) findViewById(R.id.timeStart);
        txtTimetotal= (TextView) findViewById(R.id.TimeTotal);
        txtTitle= (TextView) findViewById(R.id.tenbaihat);
        sksong = (SeekBar) findViewById(R.id.seekBar);
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
    }
}
