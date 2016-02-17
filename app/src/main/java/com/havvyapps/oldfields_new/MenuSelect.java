package com.havvyapps.oldfields_new;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

/**
 * Created by John on 12/22/2015.
 */
public class MenuSelect {

    public static void MenuSelect () {

    }

    public static void selectItem(MenuItem item, Context context) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_Home:
                intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case R.id.nav_Food:
                intent = new Intent(context, FoodActivity.class);
                context.startActivity(intent);
                break;
            case R.id.nav_Draft:
                intent = new Intent(context, DraftActivity.class);
                context.startActivity(intent);
                break;
            case R.id.nav_Beer:
                intent = new Intent(context, BeerActivity.class);
                context.startActivity(intent);
                break;
            case R.id.nav_Spirits:
                intent = new Intent(context, SpiritsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.nav_Specials:
                intent = new Intent(context, SpecialsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.nav_Contact:
                intent = new Intent(context, ContactActivity.class);
                context.startActivity(intent);
                break;
            case R.id.nav_Events:
                intent = new Intent(context, EventsActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
