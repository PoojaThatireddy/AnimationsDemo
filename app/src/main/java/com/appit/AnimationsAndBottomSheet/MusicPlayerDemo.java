package com.appit.AnimationsAndBottomSheet;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MusicPlayerDemo extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener {

        private Button buttonPlayPause;
        private SeekBar seekBarProgress;

        private MediaPlayer mediaPlayer;
        private int mediaFileLengthInMilliseconds; // this value contains the song duration in milliseconds. Look at getDuration() method in MediaPlayer class

        private final Handler handler = new Handler();

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_music_player_demo);


            buttonPlayPause = (Button)findViewById(R.id.ButtonTestPlayPause);
            buttonPlayPause.setOnClickListener(this);

            seekBarProgress = (SeekBar)findViewById(R.id.SeekBarTestPlay);
            seekBarProgress.setMax(99); // It means 100% .0-99
            seekBarProgress.setOnTouchListener(this);


            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
        }

        /** Method which updates the SeekBar progress by current song playing position*/
        private void primarySeekBarProgressUpdater() {
            seekBarProgress.setProgress((int)(((float)mediaPlayer.getCurrentPosition()/mediaFileLengthInMilliseconds)*100)); // This math construction give a percentage of "was playing"/"song length"
            if (mediaPlayer.isPlaying()) {
                Runnable notification = new Runnable() {
                    public void run() {
                        primarySeekBarProgressUpdater();
                    }
                };
                handler.postDelayed(notification,1000);
            }
        }
        @SuppressLint("ClickableViewAccessibility")

     /*   @Override
        public void onCompletion(MediaPlayer mp) {
            *//** MediaPlayer onCompletion event handler. Method which calls then song playing is complete*//*
            buttonPlayPause.setText("Play");
        }

        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            *//** Method which updates the SeekBar secondary progress by current song loading from URL position*//*
            seekBarProgress.setSecondaryProgress(percent);
        }*/

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ButtonTestPlayPause){
            /** ImageButton onClick event handler. Method which start/pause mediaplayer playing */
            try {
//                mediaPlayer.setDataSource("http://clientapp.narolainfotech.com/TestMusic/songLong.mp3"); // setup song from http://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3 URL to mediaplayer data source
                mediaPlayer.setDataSource("http://naasongsdownload.com/Telugu/2017%20naasongs" +
                        ".audio/Baahubali%202%20-%20The%20Conclusion%20(2017)%20~128Kbps-Naasongs" +
                        ".Audio/01%20-%20Saahore%20Baahubali%20-Naasongs.Audio.mp3");
                mediaPlayer.prepare(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer.
            } catch (Exception e) {
                e.printStackTrace();
            }

            mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL

            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();
                buttonPlayPause.setText("Pause");
            }else {
                mediaPlayer.pause();
                buttonPlayPause.setText("Play");
            }

            primarySeekBarProgressUpdater();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        if(v.getId() == R.id.SeekBarTestPlay){
            /** Seekbar onTouch event handler. Method which seeks MediaPlayer to seekBar primary progress position*/
            if(mediaPlayer.isPlaying()){
                SeekBar sb = (SeekBar)v;
                int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * sb.getProgress();
                mediaPlayer.seekTo(playPositionInMillisecconds);
            }
        }
        return false;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        /** Method which updates the SeekBar secondary progress by current song loading from URL position*/
        seekBarProgress.setSecondaryProgress(i);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        /** MediaPlayer onCompletion event handler. Method which calls then song playing is complete*/
        buttonPlayPause.setText("Play");
    }
}

