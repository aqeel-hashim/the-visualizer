package go.application.com.go;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import go.application.com.go.Adapter.ImageAdapter;

public class FullImageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        Intent i = getIntent();
        int position = i.getExtras().getInt("id");
        ImageAdapter adapter = new ImageAdapter(this);
        ImageView imageView = (ImageView) findViewById(R.id.topimage);
        imageView.setImageResource(adapter.images[position]);

    }

    public void gotoIR(View v)
    {
        Intent intent = new Intent(FullImageActivity.this, ImageRecognition.class);
        startActivity(intent);
    }

    public void gotograbcut(View v)

    {
        Intent intent = new Intent(FullImageActivity.this, grabcut1.class);
        startActivity(intent);
    }}

