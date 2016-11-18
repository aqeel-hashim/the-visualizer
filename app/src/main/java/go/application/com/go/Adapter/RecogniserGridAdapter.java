package go.application.com.go.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import go.application.com.go.model.Image;
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
    private ArrayList<Image> images;

    public RecogniserGridAdapter(Context c, ArrayList<Image> a){
        this.mContext = c;
        this.images = a;
    }

    @Override
    public int getCount() {
        return this.images.size();
    }

    @Override
    public Object getItem(int position) {
        return BitmapFactory.decodeFile(images.get(position).getPath());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView (mContext);
        imageView.setImageBitmap(BitmapFactory.decodeFile(images.get(position).getPath()));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new GridView.LayoutParams(240, 240));
        return imageView;
    }
}
