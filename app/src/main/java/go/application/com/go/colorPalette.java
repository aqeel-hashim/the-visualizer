package go.application.com.go;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class colorPalette extends AppCompatActivity {
    LinearLayout hview;
    int colorId;
    List<Byte> bitmapData = new ArrayList<>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_palette);
        hview=(LinearLayout) findViewById(R.id.palette);
        hview.setOnTouchListener(viewOnTouchListener);
        addFragment(android.R.id.content,
                new ColorPaletteFragment(),
                ColorPaletteFragment.FRAGMENT_TAG);
        bitmapData = Arrays.asList(toObjects(getIntent().getByteArrayExtra("byteArray")));
    }


    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }
    View.OnTouchListener viewOnTouchListener = new View.OnTouchListener() {

        @Override

        public boolean onTouch(View v, MotionEvent event) {
            try {

                ColorDrawable viewColor = (ColorDrawable) hview.getBackground();
                colorId = viewColor.getColor();
                Intent intent = new Intent(colorPalette.this, VisualizerFullActivity.class);
                byte[] array = toPrimitives(bitmapData.toArray(new Byte[bitmapData.size()]));
                Log.d("TheImageBitLengthArray", "colorClick: "+array.length);
                intent.putExtra("byteArray",array);
                intent.putExtra("SelectedColour", colorId);
                startActivity(intent);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return false;
        }
    };
    public int getColorId(){
        return colorId;
    }

    public void colorClick(View view) {


        ColorDrawable viewColor = (ColorDrawable) hview.getBackground();
        colorId =    Integer.parseInt(view.getResources().getResourceEntryName(view.getId()),16);
        //colorId = viewColor.getColor();
        String cColour = String.format("#%06X", (colorId));
        Intent intent = new Intent(colorPalette.this, VisualizerFullActivity.class);
        byte[] array = toPrimitives(bitmapData.toArray(new Byte[bitmapData.size()]));
        Log.d("TheImageBitLengthArray", "colorClick: "+array.length);
        intent.putExtra("byteArray",array);
        intent.putExtra("SelectedColour", colorId);
        startActivity(intent);

    }

    Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];

        int i = 0;
        for (byte b : bytesPrim) bytes[i++] = b; // Autoboxing

        return bytes;
    }

    byte[] toPrimitives(Byte[] oBytes)
    {
        byte[] bytes = new byte[oBytes.length];

        for(int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }

        return bytes;
    }
}


