package com.appit.AnimationsAndBottomSheet;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Appit on 9/18/2017.
 */

public class VideoModel extends ArrayList<VideoModel> {
    File videoPath;

    public VideoModel(File videoPath) {
        this.videoPath = videoPath;
    }

    public File getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(File videoPath) {
        this.videoPath = videoPath;
    }
}
