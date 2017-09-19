package com.appit.AnimationsAndBottomSheet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

import cn.jzvd.JZVideoPlayerStandard;

public class ActivityVideoTest extends AppCompatActivity implements View.OnClickListener {

    VideoView videoView;
    RecyclerView videoList;
    VideoAdapter videoAdapter;
    ArrayList<VideoModel> videoModel;
    File file = null;
    File targetFile;
    MediaPlayer mediaPlayer;
    String filename = "videos.mp4";

    JZVideoPlayerStandard jzVideoPlayerStandard;
    FileInputStream fileInputStream;
    Uri videoFileUri;
    Button captureVideoButton, playVideoButton, captureWithoutDataVideoButton;

    public static int VIDEO_CAPTURED = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_test);


        captureVideoButton = (Button) this.findViewById(R.id.CaptureVideoButton);
        playVideoButton = (Button) this.findViewById(R.id.PlayVideoButton);
        captureWithoutDataVideoButton = (Button) this.findViewById(R.id.CaptureVideoWithoutDataButton);
//        videoView = (VideoView) this.findViewById(R.id.videoView);
//        videoList=(RecyclerView)this.findViewById(R.id.video_list);

        jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.videoplayer);
//        jzVideoPlayerStandard.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");

//        mediaPlayer = new MediaPlayer();
        videoModel = new ArrayList<>();

       /* LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActivityVideoTest.this, LinearLayoutManager.VERTICAL, false);
        videoList.setLayoutManager(linearLayoutManager);

        videoList.setAdapter(videoAdapter);*/
        captureVideoButton.setOnClickListener(this);
        playVideoButton.setOnClickListener(this);
        captureWithoutDataVideoButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == captureVideoButton) {

           /* Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            File mediaFile = new File(
                    Environment.getExternalStorageDirectory().getAbsolutePath());
            videoFileUri = Uri.fromFile(mediaFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, videoFileUri);
            startActivityForResult(intent, VIDEO_CAPTURED);*/

            Intent captureVideoIntent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(captureVideoIntent, VIDEO_CAPTURED);
        }
        if (v == captureWithoutDataVideoButton) {
            playVideoButton.setEnabled(false);
            Intent captureVideoIntent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
            startActivity(captureVideoIntent);

        } else if (v == playVideoButton)
            getImagePathFromInputStreamUri(videoFileUri);
            jzVideoPlayerStandard.setUp(String.valueOf(targetFile), JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");

            /* FileInputStream in = null;
             try {
                 in = this.openFileInput(getCacheDir()+File.separator+filename);
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }

             BufferedReader br= new BufferedReader(new InputStreamReader(in));

             StringBuilder sb= new StringBuilder();
             String s= null;
             try {
                 while((s= br.readLine())!= null)  {
                     sb.append(s).append("\n");
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
             Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sb.toString()));
             intent.setDataAndType(Uri.parse(sb.toString()), "video*//*");
             startActivity(intent);*//*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(file.getAbsolutePath()));
             intent.setDataAndType(Uri.parse(file.getAbsolutePath()), "video*//*");
             startActivity(intent);*/


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private MediaPlayer.OnPreparedListener createOnPreparedListener() {
        return new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        };
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);

        if (extension != null) {
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            type = mime.getMimeTypeFromExtension(extension);
        }

        return type;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == VIDEO_CAPTURED) {
            videoFileUri = data.getData();
            playVideoButton.setEnabled(true);
//            Log.d("file path ","before = "+videoFileUri);

            saveInternalStorage(videoFileUri);


        }
    }

    private String getImagePathFromInputStreamUri(Uri videoFileUri) {
        InputStream inputStream = null;
        String filePath = null;

        if (videoFileUri.getAuthority() != null) {
            try {
                inputStream = getContentResolver().openInputStream(videoFileUri); // context needed
                File photoFile = createTemporalFileFrom(inputStream);

                filePath = photoFile.getPath();

            } catch (FileNotFoundException e) {
                // log
            } catch (IOException e) {
                // log
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return filePath;

    }

    private File createTemporalFileFrom(InputStream inputStream) throws IOException {
        targetFile = null;

        if (inputStream != null) {
            int read;
            byte[] buffer = new byte[8 * 1024];

            targetFile = createTemporalFile();
            OutputStream outputStream = new FileOutputStream(targetFile);

            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();

            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.d("Target file ","temp file = "+targetFile);
        return targetFile;

    }

    private File createTemporalFile() {
        return new File(Environment.getExternalStorageDirectory(), "tempFile.mp4"); // context needed
    }

    private File saveInternalStorage(Uri videoFileUri) {
        Bitmap finalBitmap = null;
        try {
            finalBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), videoFileUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Random generator = new Random();
            int n = 10000;
            n = generator.nextInt(n);
            file = new File(getCacheDir() + filename + n);
            FileOutputStream out = this.openFileOutput(filename, Context.MODE_WORLD_READABLE);

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "internal path = " + file, Toast.LENGTH_SHORT).show();

        Log.d("InternalPath", "video path = " + file);



        return file;


    }
}
