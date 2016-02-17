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

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context mContext = EventsActivity.this;
    private EventsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Oldfield's Events");

        final ListView list = (ListView) findViewById(R.id.events_list_view);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        ParseQuery<Events> query = ParseQuery.getQuery(Events.class);
        query.whereGreaterThanOrEqualTo("date", date);
        query.findInBackground(new FindCallback<Events>() {
            public void done(List<Events> results, ParseException e) {
                if (e == null) {
                    Log.d("Events", "Retrieved events");
                    mAdapter = new EventsListAdapter(mContext, R.layout.events_listview, results);
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
        MenuSelect.selectItem(item, EventsActivity.this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
