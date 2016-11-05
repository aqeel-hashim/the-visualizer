package go.application.com.go;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class Recognizerfirstpage extends NavigationScreen  implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    static final int CAM_REQUEST = 54321;
    Button camerabutton, gallerybutton;
    private static int RESULT_LOAD_IMAGE = 1;
    ImageView source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout content_frame = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_recognizerfirstpage, content_frame);
        camerabutton = (Button) findViewById(R.id.camerabutton);
        gallerybutton = (Button) findViewById(R.id.gallerybutton);
        source = (ImageView) findViewById(R.id.source);


        try {
            camerabutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, CAM_REQUEST);
                    }
                    Toast.makeText(getApplicationContext(), "You have asked to open camera", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        gallerybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);


            }
        });


    }

    private File getFile() {
        File folder = new File("sdcard/The Visualizer");
        if (!folder.exists()) {
            final boolean mkdir = folder.mkdir();
            if(mkdir){
                Toast.makeText(this,"Made a new directory",Toast.LENGTH_SHORT).show();
            }
        }

        return new File(folder, "cam_image.jpg");
    }


    @Override
    public void onClick(View v) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bitmap selectedphoto = null;

        Log.d("TheFuckingRequest", "onActivityResult: "+requestCode);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            String picturePath;
            int columnIndex;
            if (cursor != null) {
                columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                selectedphoto = BitmapFactory.decodeFile(picturePath);
                cursor.close();
            }
            Intent intent = new Intent(Recognizerfirstpage.this, RecognizerPage.class);
            ByteArrayOutputStream _bs = new ByteArrayOutputStream();
            if (selectedphoto != null) {
                selectedphoto.compress(Bitmap.CompressFormat.PNG, 50, _bs);
            }
            intent.putExtra("byteArray", _bs.toByteArray());

            startActivity(intent);


        }
        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK && null != data) {
            Log.d("AqeelCamRequest", "onActivityResult: "+"Opening from cam");
            Bundle extras = data.getExtras();
            selectedphoto = (Bitmap) extras.get("data");
            Intent intent = new Intent(Recognizerfirstpage.this, RecognizerPage.class);
            ByteArrayOutputStream _bs = new ByteArrayOutputStream();
            if (selectedphoto != null) {
                selectedphoto.compress(Bitmap.CompressFormat.PNG, 50, _bs);
            }
            intent.putExtra("byteArray", _bs.toByteArray());

            startActivity(intent);


        }
    }
}


