package go.application.com.go;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;

public class MainActivity extends Activity {
    Button button;
  //  ImageView imageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int CAM_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
//        button = (Button) findViewById(R.id.camera);
//       // imageView = (ImageView) findViewById(R.id.image_view);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                File file = getFile();
//
//                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
//                startActivityForResult(camera_intent, CAM_REQUEST);
//            }
//        });

    }

    private File getFile()
    {
        File folder = new File("sdcard/camera_app");
        if (!folder.exists())
        {
            folder.mkdir();
        }
        File image_file = new File (folder, "cam_image.jpg");

        return image_file;
    }
  //  @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        String path = "sdcard/camera_app/cam_image.jpg";
//       // imageView.setImageDrawable(Drawable.createFromPath(path));
//    }


    public void send(View v)
    {
        Intent intent = new Intent(MainActivity.this, Recognizerfirstpage.class);
        startActivity(intent);
    }


    public void gosomewhere(View v)
    {
        Intent intent = new Intent(MainActivity.this, VisualizerPage.class);
        startActivity(intent);
    }

    public void gotocart(View v)
    {
        Intent intent = new Intent(MainActivity.this, Cart.class);
        startActivity(intent);
    }

    public void gotologin(View v)
    {
        Intent intent = new Intent(MainActivity.this, AdminAddDetails.class);
        startActivity(intent);
    }






}
