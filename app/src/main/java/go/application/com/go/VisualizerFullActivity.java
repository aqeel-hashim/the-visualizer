package go.application.com.go;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;

public class VisualizerFullActivity extends NavigationScreen implements Runnable {

    TextView colorRGB;
    String hexColor;
    ImageView imgSource1;
    int changeColour;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if(toolbar != null){
            toolbar.setNavigationIcon(resize(ResourcesCompat.getDrawable(getResources(), R.drawable.back, null)));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //do something you want
                    startActivity(new Intent(VisualizerFullActivity.this, VisualizerPage.class));
                    finish();
                }
            });
        }
        FrameLayout content_frame = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_visualizer_full, content_frame);
        Intent intent = getIntent();
        Bitmap _bitmap = null;
        if (intent.hasExtra("byteArray")) {
            _bitmap = BitmapFactory.decodeByteArray(
                    intent.getByteArrayExtra("byteArray"), 0, intent.getByteArrayExtra("byteArray").length);
            Toast.makeText(this, "Loading Image", Toast.LENGTH_LONG).show();
        }
        imgSource1 = (ImageView) findViewById(R.id.source1);
        if (imgSource1 != null) {
            imgSource1.setImageBitmap(_bitmap);
        }
        colorRGB = (TextView) findViewById(R.id.colorrgb);
        changeColour = intent.getIntExtra("SelectedColour", changeColour);
        if (changeColour != 0) {

            imgSource1.setOnTouchListener(imgSourceOnTouchListener);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 55,55, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    View.OnTouchListener imgSourceOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            float eventX = event.getX();
            float eventY = event.getY();
            float[] eventXY = new float[]{eventX, eventY};

            Matrix invertMatrix = new Matrix();
            ((ImageView) view).getImageMatrix().invert(invertMatrix);

            invertMatrix.mapPoints(eventXY);
            int x = (int) eventXY[0];
            int y = (int) eventXY[1];

            imgSource1.buildDrawingCache();
            Bitmap bitmap = imgSource1.getDrawingCache();
            if (x < 0) {
                x = 0;
            } else if (x > bitmap.getWidth() - 1) {
                x = bitmap.getWidth() - 1;
            }

            if (y < 0) {
                y = 0;
            } else if (y > bitmap.getHeight() - 1) {
                y = bitmap.getHeight() - 1;
            }

            int touchRGB = bitmap.getPixel(x, y);

            colorRGB.setText("touched color: " + "#" + Integer.toHexString(touchRGB));
            colorRGB.setTextColor(touchRGB);
            Bitmap b = change(changeColour, touchRGB);
            imgSource1.setImageBitmap(b);
            return true;

        }
    };

    public Bitmap replaceColor(Bitmap src, int fromColor, int targetColor) {
        if (src == null) {
            return null;
        }
        // Source image size
        int width = src.getWidth();
        int height = src.getHeight();
        int[] pixels = new int[width * height];
        //get pixels
        src.getPixels(pixels, 0, width, 0, 0, width, height);

        for (int x = 0; x < pixels.length; ++x) {
            pixels[x] = (pixels[x] == fromColor) ? targetColor : pixels[x];
        }
        // create result bitmap output
        Bitmap result = Bitmap.createBitmap(width, height, src.getConfig());
        //set pixels
        result.setPixels(pixels, 0, width, 0, 0, width, height);

        return result;
    }

    public void gotopalette(View v) {
        imgSource1.buildDrawingCache();
        Bitmap bmap = imgSource1.getDrawingCache();
        ByteArrayOutputStream _bs = new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
        Intent intent = new Intent(VisualizerFullActivity.this, colorPalette.class);
        intent.putExtra("byteArray", _bs.toByteArray());
        startActivity(intent);
    }

    public Bitmap change(int changeColour, int touchColor) {
        hexColor = String.format("#%06X", (0xFFFFFF & touchColor));
        Log.d("NushraChange", "change: " + hexColor);
        String cColour = String.format("#%06X", (0xFFFFFF & changeColour));
        imgSource1.buildDrawingCache();
        Bitmap bitmap = imgSource1.getDrawingCache();
        return replaceColor(bitmap,
                Color.parseColor(hexColor), Color.parseColor(cColour));
    }

    @Override
    public void run() {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("VisualizerFull Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}