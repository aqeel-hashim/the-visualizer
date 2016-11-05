package org.opencv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class cannyEdge extends Activity {
//    public static final int ACTION_TAKE_PHOTO_B = 1;
//
//    public String mCurrentPhotoPath;
//    public static final String BITMAP_STORAGE_KEY = "viewbitmap";
//    public static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";
//    public ImageView mImageView;
// //   private float lowThreshold;
//  //  private float highThreshold;
//    public Bitmap mImageBitmap;
//    public static final String JPEG_FILE_PREFIX = "IMG_";
//    public static final String JPEG_FILE_SUFFIX = ".jpg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canny_edge);
//
//        mImageView = (ImageView) findViewById(R.id.imageView1);
//        mImageBitmap = null;
//
//        Button picBtn = (Button) findViewById(R.id.btnIntend);
//        setBtnListenerOrDisable(
//                picBtn,
//                mTakePicOnClickListener,
//                MediaStore.ACTION_IMAGE_CAPTURE
//        );
//
//    }
//
//    /**
//     * Click method to handle user pressing button to launch camera
//     */
//
//    Button.OnClickListener mTakePicOnClickListener =
//            new Button.OnClickListener() {
//                public void onClick(View v) {
//                    dispatchTakePictureIntent(ACTION_TAKE_PHOTO_B);
//                }
//            };
//
//
//    public File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
//        //File albumF = getAlbumDir();
//        File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX);
//        return imageF;
//    }
//
//    public File setUpPhotoFile() throws IOException {
//
//        File f = createImageFile();
//        mCurrentPhotoPath = f.getAbsolutePath();
//
//        return f;
//    }
//
//    public void setPic() {
//
//    /* There isn't enough memory to open up more than a couple camera photos */
//    /* So pre-scale the target bitmap into which the file is decoded */
//
//    /* Get the size of the ImageView */
//        int targetW = mImageView.getWidth();
//        int targetH = mImageView.getHeight();
//
//    /* Get the size of the image */
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        bmOptions.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
//        int photoW = bmOptions.outWidth;
//        int photoH = bmOptions.outHeight;
//
//    /* Figure out which way needs to be reduced less */
//        int scaleFactor = 2;
//        if ((targetW > 0) || (targetH > 0)) {
//            scaleFactor = Math.min(photoW / targetW, photoH / targetH);
//        }
//
//    /* Set bitmap options to scale the image decode target */
//        bmOptions.inJustDecodeBounds = false;
//        bmOptions.inSampleSize = scaleFactor;
//        bmOptions.inPurgeable = true;
//
//    /* Decode the JPEG file into a Bitmap */
//        mImageBitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
//        CannyEdgeDetection detector = new CannyEdgeDetection();
//        detector.setLowThreshold(0.5f);
//        detector.setHighThreshold(1f);
//        detector.setSourceImage(mImageBitmap);
//        detector.process();
//        Bitmap edges = detector.getEdgesImage();
//    /* Associate the Bitmap to the ImageView */
//        mImageView.setImageBitmap(edges);
//        mImageView.setVisibility(View.VISIBLE);
//    }
//
//    public void setSourceImage (Bitmap mImageBitmap) {
//        mImageBitmap = mImageBitmap.copy(Bitmap.Config.ARGB_8888, true);
//    }
//
//
//    public void galleryAddPic() {
//        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }
//
//    public void dispatchTakePictureIntent(int actionCode) {
//
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        File f = null;
//
//        try {
//            f = setUpPhotoFile();
//            mCurrentPhotoPath = f.getAbsolutePath();
//            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
//        } catch (IOException e) {
//            e.printStackTrace();
//            f = null;
//            mCurrentPhotoPath = null;
//        }
//
//
//        startActivityForResult(takePictureIntent, 1102);
//    }
//
//    public void handleBigCameraPhoto() {
//        if (mCurrentPhotoPath != null) {
//            setPic();
//            galleryAddPic();
//            mCurrentPhotoPath = null;
//        }
//    }
//
//    /**
//     * Handle user returning from capturing the image
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 1102 && resultCode == Activity.RESULT_OK) {
//            if (data != null) {
//                handleBigCameraPhoto();
//            }
//        }
//
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putParcelable(BITMAP_STORAGE_KEY, mImageBitmap);
//        outState.putBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY, (mImageBitmap != null));
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        mImageBitmap = savedInstanceState.getParcelable(BITMAP_STORAGE_KEY);
//        mImageView.setImageBitmap(mImageBitmap);
//        mImageView.setVisibility(savedInstanceState.getBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY) ? ImageView.VISIBLE : ImageView.INVISIBLE);
//    }
//
//    public static boolean isIntentAvailable(Context context, String action) {
//        final PackageManager packageManager = context.getPackageManager();
//        final Intent intent = new Intent(action);
//        List<ResolveInfo> list =
//                packageManager.queryIntentActivities(intent,
//                        PackageManager.MATCH_DEFAULT_ONLY);
//        return list.size() > 0;
//    }
//
//
//    public void setBtnListenerOrDisable(
//            Button btn,
//            Button.OnClickListener onClickListener,
//            String intentName
//    ) {
//        if (isIntentAvailable(this, intentName)) {
//            btn.setOnClickListener(onClickListener);
//        } else {
//            btn.setText(
//                    getText(R.string.cannot).toString() + " " + btn.getText());
//            btn.setClickable(false);
//        }
    }
}
