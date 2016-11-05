package go.application.com.go;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class RecognizerPage extends AppCompatActivity {

    ImageView image;
    Button similarbutton;
    TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognizer_page);
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
                    startActivity(new Intent(RecognizerPage.this, Recognizerfirstpage.class));
                    finish();
                }
            });
        }
        Intent intent = getIntent();
        Bitmap _bitmap = null;
        if(intent.hasExtra("byteArray")) {
            _bitmap = BitmapFactory.decodeByteArray(
                    intent.getByteArrayExtra("byteArray"),0,intent.getByteArrayExtra("byteArray").length);
            Toast.makeText(this,"Loading Image",Toast.LENGTH_LONG);
        }
        image = (ImageView) findViewById(R.id.source1);
        image.setImageBitmap(_bitmap);

        similarbutton = (Button) findViewById(R.id.showsimilarbutton);
        view = (TextView) findViewById(R.id.resultText);
        view.setText("Floral");
        similarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerPage.this,RecogniserGrid.class);
                intent.putExtra("type","Floral");
                startActivity(intent);

            }
        });

    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 55,55, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }
}

