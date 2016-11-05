package go.application.com.go.Adapter;


import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import go.application.com.go.R;

public class ImageAdapter extends BaseAdapter{

    private Context context;
    public Integer[] images = {
    R.drawable.ck, R.drawable.fb,
    R.drawable.gd, R.drawable.levis,
    R.drawable.no, R.drawable.odel
    };

    public ImageAdapter(Context c)

    {
        context = c;
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView (context);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new GridView.LayoutParams(240, 240));
        return imageView;

    }

}
