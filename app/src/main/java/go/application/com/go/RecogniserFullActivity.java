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
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import go.application.com.go.model.Image;

public class RecogniserFullActivity extends NavigationScreen {

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
                    startActivity(new Intent(RecogniserFullActivity.this, RecogniserGrid.class).putExtra("type",getIntent().getStringExtra("type")));
                    finish();
                }
            });
        }
        FrameLayout content_frame = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_recogniser_full, content_frame);
        setContentView(R.layout.activity_recogniser_full);
        Image image = (Image) getIntent().getSerializableExtra("Image");
        ImageView imageView = ((ImageView) findViewById(R.id.source1));
        imageView.setImageBitmap(BitmapFactory.decodeFile(image.getPath()));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        TextView name = (TextView) findViewById(R.id.nameText);
        name.setText(image.getName());
        TextView retailer = (TextView) findViewById(R.id.retailerText);
        retailer.setText(image.getRatailer());
        TextView address = (TextView) findViewById(R.id.addressText);
        address.setText(image.getAddress());
        TextView contact = (TextView) findViewById(R.id.contactText);
        contact.setText(image.getContact());

    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 55,55, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }
}
