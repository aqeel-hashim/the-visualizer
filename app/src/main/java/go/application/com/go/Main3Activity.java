package go.application.com.go;

import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Main3Activity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_opened, R.string.drawer_closed);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new HomeFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home Fragment");
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public  boolean onNavigationItemSelected(MenuItem item){
                switch(item.getItemId())
                {
                    case R.id.id_home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new HomeFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Home Fragment");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;

                    case R.id.id_recognizer:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new RecognizerFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Recognizer Fragment");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;


                    case R.id.id_visualizer:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new VisualizerFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Visualizer Fragment");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;

                    case R.id.id_colorpalette:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new ColorPaletteFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("ColorPalette Fragment");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;

                    case R.id.id_products:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new ProductsFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Products Fragment");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;

                    case R.id.id_login:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new LoginFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Login Fragment");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;


                }

                return false;
            }
        });

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();

    }
}
