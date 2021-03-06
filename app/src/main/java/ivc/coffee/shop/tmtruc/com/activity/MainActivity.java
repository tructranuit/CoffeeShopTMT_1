package ivc.coffee.shop.tmtruc.com.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ivc.coffee.shop.tmtruc.com.R;
import ivc.coffee.shop.tmtruc.com.fragment.HomeFragment;
import ivc.coffee.shop.tmtruc.com.fragment.MenuFragment;
import ivc.coffee.shop.tmtruc.com.fragment.OrderFragment;
import ivc.coffee.shop.tmtruc.com.util.ActivityUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HomeFragment homeFragment = new HomeFragment();
        // load home fragment
        ActivityUtils.loadFragmentToActivity(
                getSupportFragmentManager(), homeFragment, R.id.fragment_container);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // load home fragment
            HomeFragment homeFragment = new HomeFragment();
            ActivityUtils.loadFragmentToActivity(
                    getSupportFragmentManager(), homeFragment, R.id.fragment_container);
        } else if (id == R.id.nav_menu) {
            //load menu fragment
            MenuFragment menuFragment = new MenuFragment();
            ActivityUtils.loadFragmentToActivity(
                    getSupportFragmentManager(), menuFragment, R.id.fragment_container);
        } else if (id == R.id.nav_order) {
            //load order fragment
            OrderFragment orderFragment = new OrderFragment();
            ActivityUtils.loadFragmentToActivity(
                    getSupportFragmentManager(), orderFragment, R.id.fragment_container);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
