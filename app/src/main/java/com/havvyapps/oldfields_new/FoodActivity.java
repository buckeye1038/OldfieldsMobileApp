package com.havvyapps.oldfields_new;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context mContext = FoodActivity.this;
    private ArrayList<Food> mResults;
    private FoodListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Food Menu");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final ListView list = (ListView) findViewById(R.id.food_list);

        mResults = new ArrayList<>();
        ParseQuery<Food> query = ParseQuery.getQuery(Food.class);
        query.findInBackground(new FindCallback<Food>() {
            public void done(List<Food> results, ParseException e) {
                if (e == null) {
                    Log.d("Food", "Retrieved food menu items");
                    for (Food item : results) {
                        if (item.getActive()) {
                            mResults.add(item);
                        }
                    }
                    mAdapter = new FoodListAdapter(mContext, R.layout.food_listview, mResults);
                    list.setAdapter(mAdapter);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        MenuSelect.selectItem(item, FoodActivity.this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
