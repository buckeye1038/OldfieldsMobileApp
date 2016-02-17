package com.havvyapps.oldfields_new;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by John on 12/31/2015.
 */
public class App extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        ParseObject.registerSubclass(Food.class);
        ParseObject.registerSubclass(Alcohol.class);
        ParseObject.registerSubclass(Specials.class);
        ParseObject.registerSubclass(Events.class);
    }

}
