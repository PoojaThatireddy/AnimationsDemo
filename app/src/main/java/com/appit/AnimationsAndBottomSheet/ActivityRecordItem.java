package com.appit.AnimationsAndBottomSheet;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class ActivityRecordItem extends AppCompatActivity implements View.OnClickListener {

    private ImageView playId,pauseId;
    private TextView recordTimerId;
    private SeekBar seekBarId;
    private ProgressBar progressBarId;

    private Handler myHandler = new Handler();
    private MediaPlayer mediaPlayer;

    boolean isPausing=false;
    private double startTime = 0;
    private double finalTime = 0;

    public static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_item);

        playId=(ImageView)findViewById(R.id.play_record_id);
        pauseId=(ImageView)findViewById(R.id.pause_record_id);
        recordTimerId=(TextView) findViewById(R.id.record_timer_id);
        seekBarId=(SeekBar) findViewById(R.id.seekBar_id);
        progressBarId=(ProgressBar) findViewById(R.id.progressbar_id);

        mediaPlayer = MediaPlayer.create(this, R.raw.bahubali_song);

        double audioTime=mediaPlayer.getDuration();
        recordTimerId.setText(String.format("%d : %d ",
                TimeUnit.MILLISECONDS.toMinutes((long) audioTime),
                TimeUnit.MILLISECONDS.toSeconds((long) audioTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                toMinutes((long) audioTime)))
        );
        seekBarId.setMax(mediaPlayer.getDuration());
        seekBarId.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                changeSeekbarTime(view);
                return false;
            }
        });

        playId.setOnClickListener(this);
        pauseId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPausing=true;
                mediaPlayer.pause();
                pauseId.setVisibility(View.INVISIBLE);
                playId.setVisibility(View.VISIBLE);
                myHandler.postDelayed(UpdateSongTime, 100);
            }
        });
    }
    private void changeSeekbarTime(View view) {
        if (mediaPlayer.isPlaying()) {
            SeekBar sb = (SeekBar) view;
            mediaPlayer.seekTo(sb.getProgress());
        }else if(isPausing){
            SeekBar sb=(SeekBar)view;
            mediaPlayer.seekTo(sb.getProgress());
        }
    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            recordTimerId.setText(String.format("%d : %d ",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekBarId.setProgress((int) startTime);
            myHandler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onClick(View view) {
        mediaPlayer.start();

        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();

        if (oneTimeOnly == 0) {
            seekBarId.setMax((int) finalTime);
            oneTimeOnly = 1;
        }

       /* tx2.setText(String.format("%d : %d ",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                finalTime)))
        );*/

        recordTimerId.setText(String.format("%d : %d ",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                startTime)))
        );

        seekBarId.setProgress((int) startTime);
        myHandler.postDelayed(UpdateSongTime, 100);
        pauseId.setVisibility(View.VISIBLE);
        playId.setVisibility(View.INVISIBLE);
    }
}
