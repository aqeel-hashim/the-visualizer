package go.application.com.go;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;

import go.application.com.go.Adapter.CartAdapter;

public class Cart extends NavigationScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout content_frame = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_cart, content_frame);
            GridView gridView = (GridView) findViewById(R.id.gridView);

        if (gridView != null) {
            gridView.setAdapter(new CartAdapter(this));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(getApplicationContext(), CartFullActivity.class);
                    i.putExtra("id", position);
                    startActivity(i);
                }
            });
        }
        }


}