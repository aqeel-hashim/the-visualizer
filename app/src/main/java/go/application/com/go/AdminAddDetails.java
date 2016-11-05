package go.application.com.go;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import go.application.com.go.Utilities.DatabaseOperations;

public class AdminAddDetails extends AppCompatActivity {

    Button opengallery, save;
    EditText name, type, retailer, address, contact;
    ImageView image;
    String filepath;
    private static int RESULT_LOAD_IMAGE = 1;
    private DatabaseOperations db;
    private Bitmap imageStored = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_details);
        name = (EditText) findViewById(R.id.name);
        type = (EditText) findViewById(R.id.type);
        retailer = (EditText) findViewById(R.id.retailer);
        address = (EditText) findViewById(R.id.address);
        contact = (EditText) findViewById(R.id.contact);
        opengallery = (Button) findViewById(R.id.opengallery);
        save = (Button) findViewById(R.id.save);
        db = new DatabaseOperations(this);
        opengallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            this.filepath = getRealPathFromURI(selectedImage);

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            this.imageStored = BitmapFactory.decodeFile(picturePath);
            this.image = (ImageView) findViewById(R.id.image);
            this.image.setImageBitmap(imageStored);



        }

    }

    public void saveToDb(View view) {
        ByteArrayOutputStream _bs = new ByteArrayOutputStream();
        imageStored.compress(Bitmap.CompressFormat.PNG, 50, _bs);
        db.addImage(this.db, this.filepath,this.name.getText().toString(),this.type.getText().toString(),this.retailer.getText().toString(),this.address.getText().toString(), this.contact.getText().toString());

    }

    public String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
