package go.application.com.go;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import org.opencv.android.OpenCVLoader;

public class OpenCV extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    static
    {
        if (!OpenCVLoader.initDebug()){
            Log.d(TAG, "OpenCV not loaded");
        }
        else {
            Log.d(TAG, "OpenCV loaded");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cv);
    }
}
