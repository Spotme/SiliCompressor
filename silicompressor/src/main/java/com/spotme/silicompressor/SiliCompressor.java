package com.spotme.silicompressor;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

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
     * @return The Path of the compressed video file
     */
    public static String compressVideo(String videoFilePath, String destinationDir) throws URISyntaxException {
        return compressVideo(videoFilePath, destinationDir, 0, 0, 0);
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
     * @return The Path of the compressed video file
     */
    public static String compressVideo(String videoFilePath, String destinationDir, int outWidth, int outHeight, int bitrate) throws URISyntaxException {
        boolean converted = MediaController.getInstance().convertVideo(null, null, videoFilePath, new File(destinationDir), outWidth, outHeight, bitrate);
        if (converted) {
            Timber.d("Video Conversion Complete");
        } else {
            Timber.d("Video conversion in progress");
        }

        return MediaController.cachedFile.getPath();
    }

    /**
     * Perform background video compression. Make sure the videofileUri and destinationUri are valid
     * resources because this method does not account for missing directories hence your converted file
     * could be in an unknown location
     * This uses default values for the converted videos
     *
     * @param uri            uri for the video file
     * @param destinationDir destination directory where converted file should be saved
     * @return The Path of the compressed video file
     */
    public static String compressVideo(Context context, Uri uri, String destinationDir) throws URISyntaxException {
        return compressVideo(context, uri, destinationDir, 0, 0, 0);
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
     * @return The Path of the compressed video file
     */
    public static String compressVideo(Context context, Uri uri, String destinationDir, int outWidth, int outHeight, int bitrate) throws URISyntaxException {
        boolean converted = MediaController.getInstance().convertVideo(context, uri, null, new File(destinationDir), outWidth, outHeight, bitrate);
        if (converted) {
            Timber.d("Video Conversion Complete");
        } else {
            Timber.d("Video conversion in progress");
        }

        return MediaController.cachedFile.getPath();
    }
}
