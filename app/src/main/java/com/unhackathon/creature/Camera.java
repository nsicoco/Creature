package com.unhackathon.creature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.implementation.AndroidImage;

/**
 * Created by Noel on 9/20/2014.
 */
public class Camera {
    public static final int TAKE_PICTURE_REQUEST_CODE = 100;
    private static Bitmap capturedImage;
    private static AndroidImage ai;
    private final Activity activity;

    public Camera(Activity a) {
        activity = a;
    }

    public void takePicture(Activity a) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        a.startActivityForResult(i, TAKE_PICTURE_REQUEST_CODE);
    }

    public void pictureTaken(int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            //Image Captured
            capturedImage = (Bitmap) data.getExtras().get("data");
            ai = new AndroidImage(capturedImage, Graphics.ImageFormat.RGB565);
        } else if (resultCode == Activity.RESULT_CANCELED) {
            //User cancelled the image capture
        } else {
            //Capture failed
            Toast.makeText(activity, "Image Capture Failed.", Toast.LENGTH_LONG).show();
        }
    }

    public static Bitmap getCapturedImage() {
        return capturedImage;
    }

    public static AndroidImage getCapturedAndroidImage() {
        return ai;
    }
}
