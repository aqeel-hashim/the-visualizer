package go.application.com.go.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by epc on 10/23/2016.
 */
public class RecogniserGridAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<byte[]> images;

    RecogniserGridAdapter(Context c, ArrayList<byte[]> a){
        this.mContext = c;
        this.images = a;
    }

    @Override
    public int getCount() {
        return this.images.size();
    }

    @Override
    public Object getItem(int position) {
        return BitmapFactory.decodeByteArray(this.images.get(position),0,this.images.get(position).length);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView (mContext);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(this.images.get(position),0,this.images.get(position).length));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new GridView.LayoutParams(240, 240));
        return imageView;
    }
}
