package com.havvyapps.oldfields_new;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Class to handle Specials objects
 */
@ParseClassName("Specials")
public class Specials extends ParseObject {

    public Specials() {
        // A default constructor is required.
    }

    public String getName() {
        return getString("name");
    }

    public String getDescription() {
        return getString("special");
    }

    public String getDay() {
        return getString("day");
    }

    public Boolean getActive() {
        return getBoolean("active");
    }
}
