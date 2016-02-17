package com.havvyapps.oldfields_new;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SpecialsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private LinearLayout mLayout;
    private Context mContext = SpecialsActivity.this;
    private SpecialsListAdapter mAdapter;
    private ArrayList<Specials> mon;
    private ArrayList<Specials> tues;
    private ArrayList<Specials> wed;
    private ArrayList<Specials> thurs;
    private ArrayList<Specials> fri;
    private ArrayList<Specials> sat;
    private ArrayList<Specials> sun;
    private ArrayList<Specials> week;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specials_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Weekly Specials");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mon = new ArrayList<>();
        tues = new ArrayList<>();
        wed = new ArrayList<>();
        thurs = new ArrayList<>();
        fri = new ArrayList<>();
        sat = new ArrayList<>();
        sun = new ArrayList<>();
        week = new ArrayList<>();

        query();
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
        MenuSelect.selectItem(item, SpecialsActivity.this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void query (){

        ParseQuery<Specials> query = ParseQuery.getQuery(Specials.class);
        query.whereEqualTo("active", true);
        query.findInBackground(new FindCallback<Specials>() {
            public void done(List<Specials> results, ParseException e) {
                if (e == null) {
                    Log.d("Specials", "Retrieved Specials menu items");
                    for (Specials item : results) {
                        switch (item.getDay()) {
                            case "mon":
                                mon.add(item);
                                break;
                            case "tue":
                                tues.add(item);
                                break;
                            case "wed":
                                wed.add(item);
                                break;
                            case "thu":
                                thurs.add(item);
                                break;
                            case "fri":
                                fri.add(item);
                                break;
                            case "sat":
                                sat.add(item);
                                break;
                            case "sun":
                                sun.add(item);
                                break;
                            case "week":
                                week.add(item);
                                break;
                        }
                    }

                    mLayout = (LinearLayout) findViewById(R.id.specials_layout);

                    mAdapter = new SpecialsListAdapter(mContext, R.layout.specials_listview, week);
                    makeView(mLayout, "Weekly");

                    mAdapter = new SpecialsListAdapter(mContext, R.layout.specials_listview, mon);
                    makeView(mLayout, "Monday");

                    mAdapter = new SpecialsListAdapter(mContext, R.layout.specials_listview, tues);
                    makeView(mLayout, "Tuesday");

                    mAdapter = new SpecialsListAdapter(mContext, R.layout.specials_listview, wed);
                    makeView(mLayout, "Wednesday");

                    mAdapter = new SpecialsListAdapter(mContext, R.layout.specials_listview, thurs);
                    makeView(mLayout, "Thursday");

                    mAdapter = new SpecialsListAdapter(mContext, R.layout.specials_listview, fri);
                    makeView(mLayout, "Friday");

                    mAdapter = new SpecialsListAdapter(mContext, R.layout.specials_listview, sat);
                    makeView(mLayout, "Saturday");

                    mAdapter = new SpecialsListAdapter(mContext, R.layout.specials_listview, sun);
                    makeView(mLayout, "Sunday");

                } else {
                    Log.d("Specials", "Error: " + e.getMessage());
                }
            }
        });
    }

    public void makeView(LinearLayout layout, String day){
        layout = (LinearLayout) findViewById(R.id.specials_layout);
        TextView textView = new TextView(SpecialsActivity.this);
        textView.setText(day);
        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        layout.addView(textView);
        setSpecials(layout, mAdapter);
    }

    public void setSpecials (LinearLayout layout, SpecialsListAdapter adapter){

        final int adapterCount = adapter.getCount();
        for (int i = 0; i < adapterCount; i++) {
            View item = adapter.getView(i, null, null);
            layout.addView(item);
        }
    }
}
