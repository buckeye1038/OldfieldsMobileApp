package com.havvyapps.oldfields_new;

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

import java.util.List;

public class BeerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context mContext = BeerActivity.this;
    private AlcoholListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Ohio Beers");

        final ListView list = (ListView) findViewById(R.id.beer_list_view);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ParseQuery<Alcohol> query = ParseQuery.getQuery(Alcohol.class);
        query.whereEqualTo("active", true);
        query.whereNotEqualTo("form", "spirits");
        query.whereNotEqualTo("form", "draft");
        query.findInBackground(new FindCallback<Alcohol>() {
            public void done(List<Alcohol> results, ParseException e) {
                if (e == null) {
                    Log.d("Beer", "Retrieved beer menu items");
                    mAdapter = new AlcoholListAdapter(mContext, R.layout.alcohol_listview, results);
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
        MenuSelect.selectItem(item, BeerActivity.this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
