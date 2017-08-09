package com.appit.AnimationsAndBottomSheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UrlMediaPlayer extends AppCompatActivity {
    public final static String AUDIO_URL = "audio_url";
    public final static String IMG_URL = "image_url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_media_player);
    }

    /** open player  */
    public void openPlayer(View view) {
        Intent intent = new Intent(this, UrlMediaPlayer1.class);
        intent.putExtra(AUDIO_URL, "https://archive.org/details/testmp3testfile");
        intent.putExtra(IMG_URL, "https://pixabay.com/en/heart-sweetheart-leaf-autumn-maple-1776746/");
        startActivity(intent);
    }
}
