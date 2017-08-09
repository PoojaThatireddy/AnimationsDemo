package com.appit.AnimationsAndBottomSheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UrlMediaPlayer extends AppCompatActivity {
    public final static String AUDIO_URL=null;
    public final static String IMG_URL = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_media_player);
    }

    /** open player  */
    public void openPlayer(View view) {
        Intent intent = new Intent(this, UrlMediaPlayer1.class);
        intent.putExtra(AUDIO_URL, "http://naasongsdownload.com/Telugu/2017%20naasongs" +
        ".audio/Baahubali%202%20-%20The%20Conclusion%20(2017)%20~128Kbps-Naasongs" +
                ".Audio/01%20-%20Saahore%20Baahubali%20-Naasongs.Audio.mp3");
        intent.putExtra(IMG_URL, "https://pixabay.com/en/heart-sweetheart-leaf-autumn-maple-1776746/");
        startActivity(intent);
    }
}
