package go.application.com.go;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import go.application.com.go.Adapter.CartAdapter;

public class CartFullActivity extends Activity  implements View.OnTouchListener {
    private static int RESULT_LOAD_IMAGE = 1;
    private static final int RQS_OPEN_IMAGE = 1;
    //these matrices will be used to move and zoom image
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    public Button button;
    private int mode = NONE;
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private float[] lastEvent = null;
    String mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_full);

        Intent i = getIntent();
        int position = i.getExtras().getInt("id");
        CartAdapter adapter = new CartAdapter(this);

        Button trybutton= (Button) findViewById(R.id.trybutton);

        ImageView topimage = (ImageView) findViewById(R.id.topimage);
        topimage.setImageResource(adapter.images[position]);

        topimage.setOnTouchListener(this);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            mData = extras.getString("key");

        }
    }

    public void showtrydialog(View v)
    {

//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//        alertDialog.setTitle("Photo Recognizer");
//        alertDialog.setMessage("Whats is the backdrop image?");
//        alertDialog.setPositiveButton("Open Gallery", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                Toast.makeText(getApplicationContext(), "You have asked to open gallery", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
           try {
               Uri selectedImage = data.getData();
               String[] filePathColumn = {MediaStore.Images.Media.DATA};

               Cursor cursor = getContentResolver().query(selectedImage,
                       filePathColumn, null, null, null);
               cursor.moveToFirst();

               int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
               String picturePath = cursor.getString(columnIndex);
               cursor.close();

               ImageView bottomimage = (ImageView) findViewById(R.id.bottomimage);
               bottomimage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
           }
           catch (Exception e)
           {
               e.printStackTrace();
           }
        }
    }

    //Multitouch
    public boolean onTouch(View v, MotionEvent event) {
// handle touch events here
        ImageView topimage = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;
            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    matrix.postTranslate(dx, dy);
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = (newDist / oldDist);
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }

                    if (lastEvent != null && event.getPointerCount() == 3) {

                        newRot = rotation(event);
                        float r = newRot - d;
                        float[] values = new float[9];
                        matrix.getValues(values);
                        float tx = values[2];
                        float ty = values[5];
                        float sx = values[0];
                        float xc = (topimage.getWidth() / 2) * sx;
                        float yc = (topimage.getHeight() / 2) * sx;

                        matrix.postRotate(r, tx + xc, ty + yc);
                    }
                }
                break;
        }

        topimage.setImageMatrix(matrix);
        return true;
    }
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

}



