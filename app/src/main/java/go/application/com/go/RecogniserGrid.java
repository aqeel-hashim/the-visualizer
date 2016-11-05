package go.application.com.go;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import go.application.com.go.Adapter.RecogniserGridAdapter;
import go.application.com.go.Utilities.DatabaseOperations;

public class RecogniserGrid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recogniser_grid);
        GridView grid = (GridView) findViewById(R.id.recongiser_Grid);
        Intent i = getIntent();
        String type = i.getStringExtra("type");
        DatabaseOperations dop = new DatabaseOperations(this);
        Cursor cr = dop.getImages(dop);
        ArrayList<byte[]> images = new ArrayList<>();
        cr.moveToFirst();
        do {
            if (type.equals(cr.getString(1))) {
                Bitmap pic = BitmapFactory.decodeFile(cr.getString(0));
                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                pic.compress(Bitmap.CompressFormat.PNG, 50, _bs);
                images.add(_bs.toByteArray());
            }
        }
        while (cr.moveToNext());

        grid.setAdapter(new RecogniserGridAdapter(this, images));


    }
}
