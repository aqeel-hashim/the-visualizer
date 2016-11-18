package go.application.com.go;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import go.application.com.go.Adapter.RecogniserGridAdapter;
import go.application.com.go.Utilities.DatabaseOperations;
import go.application.com.go.model.Image;
public class RecogniserGrid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recogniser_grid);
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
                    startActivity(new Intent(RecogniserGrid.this, RecognizerPage.class));
                    finish();
                }
            });
        }
        GridView grid = (GridView) findViewById(R.id.recongiser_Grid);
        Intent i = getIntent();
        final String type = i.getStringExtra("type");
        DatabaseOperations dop = new DatabaseOperations(this);
        Cursor cr = dop.getImages(dop);
        final ArrayList<Image> images = new ArrayList<>();
        cr.moveToFirst();
        do {
            if (type.equals(cr.getString(2))) {
                images.add(getImage(cr));
            }
        }
        while (cr.moveToNext());

        grid.setAdapter(new RecogniserGridAdapter(this, images));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RecogniserGrid.this, RecogniserFullActivity.class);
                intent.putExtra("type",type);
                intent.putExtra("Image", images.get(position));
                startActivity(intent);


            }
        });


    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 55,55, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    private Image getImage(Cursor cr){

        return new Image(cr.getString(0),cr.getString(1),cr.getString(2),cr.getString(3),cr.getString(4),cr.getString(5));
    }
}
