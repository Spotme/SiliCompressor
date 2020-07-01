package com.spotme.silicompressor;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.spotme.silicompressor.videocompression.MediaController;

import java.io.File;
import java.net.URISyntaxException;

import timber.log.Timber;

public class SiliCompressor {

    private static final String FILE_PROVIDER_AUTHORITY = ".spotme.silicompressor.provider";

    static String getAuthorities(@NonNull Context context) {
        return context.getPackageName() + FILE_PROVIDER_AUTHORITY;
    }

    /**
     * Perform background video compression. Make sure the videofileUri and destinationUri are valid
     * resources because this method does not account for missing directories hence your converted file
     * could be in an unknown location
     * This uses default values for the converted videos
     *
     * @param videoFilePath  source path for the video file
     * @param destinationDir destination directory where converted file should be saved
     * @return The compressed video file
     */
    public static File compressVideo(String videoFilePath, String destinationDir, @Nullable String name) throws URISyntaxException {
        return compressVideo(videoFilePath, destinationDir, name, 0, 0, 0);
    }


    /**
     * Perform background video compression. Make sure the videofileUri and destinationUri are valid
     * resources because this method does not account for missing directories hence your converted file
     * could be in an unknown location
     *
     * @param videoFilePath  source path for the video file
     * @param destinationDir destination directory where converted file should be saved
     * @param outWidth       the target width of the compressed video or 0 to use default width
     * @param outHeight      the target height of the compressed video or 0 to use default height
     * @param bitrate        the target bitrate of the compressed video or 0 to user default bitrate
     * @return The compressed video file
     */
    public static File compressVideo(String videoFilePath, String destinationDir, @Nullable String name, int outWidth, int outHeight, int bitrate) throws URISyntaxException {
        boolean converted = MediaController.getInstance().convertVideo(null, null, videoFilePath, new File(destinationDir), name, outWidth, outHeight, bitrate);
        if (converted) {
            Timber.d("Video Conversion Complete");
        } else {
            Timber.d("Video conversion in progress");
        }

        return MediaController.cachedFile;
    }

    /**
     * Perform background video compression. Make sure the videofileUri and destinationUri are valid
     * resources because this method does not account for missing directories hence your converted file
     * could be in an unknown location
     * This uses default values for the converted videos
     *
     * @param uri            uri for the video file
     * @param destinationDir destination directory where converted file should be saved
     * @return The compressed video file
     */
    public static File compressVideo(Context context, Uri uri, String destinationDir, @Nullable String name) throws URISyntaxException {
        return compressVideo(context, uri, destinationDir, name, 0, 0, 0);
    }


    /**
     * Perform background video compression. Make sure the videofileUri and destinationUri are valid
     * resources because this method does not account for missing directories hence your converted file
     * could be in an unknown location
     *
     * @param uri            uri for the video file
     * @param destinationDir destination directory where converted file should be saved
     * @param outWidth       the target width of the compressed video or 0 to use default width
     * @param outHeight      the target height of the compressed video or 0 to use default height
     * @param bitrate        the target bitrate of the compressed video or 0 to user default bitrate
     * @return The compressed video file
     */
    public static File compressVideo(Context context, Uri uri, String destinationDir, @Nullable String name, int outWidth, int outHeight, int bitrate) throws URISyntaxException {
        boolean converted = MediaController.getInstance().convertVideo(context, uri, null, new File(destinationDir), name, outWidth, outHeight, bitrate);
        if (converted) {
            Timber.d("Video Conversion Complete");
        } else {
            Timber.d("Video conversion in progress");
        }

        return MediaController.cachedFile;
    }
}
