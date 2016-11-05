package go.application.com.go.Adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import go.application.com.go.R;

public class CartAdapter extends BaseAdapter

{

    private Context context;
    public Integer[] images = {
            R.drawable.wp1,
            R.drawable.wp2,R.drawable.floral,
            R.drawable.wallcladding, R.drawable.wallcladding2,
            R.drawable.wood,R.drawable.painting,
            R.drawable.painting2,
            R.drawable.ck,R.drawable.floral1,
            R.drawable.floral2,R.drawable.floral4,
            R.drawable.floral5,R.drawable.floral6
    };

    public CartAdapter(Context c)

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
