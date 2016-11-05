package go.application.com.go;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import go.application.com.go.Adapter.CartAdapter;

public class Cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
//
//        addFragment(android.R.id.content,
//                new ProductsFragment(),
//                ProductsFragment.FRAGMENT_PRODUCTS);

//        try
//        {
            GridView gridView = (GridView) findViewById(R.id.gridView);

            gridView.setAdapter(new CartAdapter(this));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(getApplicationContext(), CartFullActivity.class);
                    i.putExtra("id", position);
                    startActivity(i);
                }
            });
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
        }



//
//    //////////start fragment/////////////////
//    protected void addFragment(@IdRes int containerViewId,
//                               @NonNull Fragment fragment,
//                               @NonNull String fragmentTag) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(containerViewId, fragment, fragmentTag)
//                .disallowAddToBackStack()
//                .commit();
//    }
//
//    protected void replaceFragment(@IdRes int containerViewId,
//                                   @NonNull Fragment fragment,
//                                   @NonNull String fragmentTag,
//                                   @Nullable String backStackStateName) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(containerViewId, fragment, fragmentTag)
//                .addToBackStack(backStackStateName)
//                .commit();
//    }
//
//// end fragment//////////

}