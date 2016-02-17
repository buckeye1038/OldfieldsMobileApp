package com.havvyapps.oldfields_new;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Class to handle Food objects
 */
@ParseClassName("Alcohol")
public class Alcohol extends ParseObject {

    public Alcohol() {
        // A default constructor is required.
    }

    public String getName() {
        return getString("name");
    }

    public String getDescription() {
        return getString("description");
    }

    public Boolean getActive() {
        return getBoolean("active");
    }

    public String getForm(){
        return getString("form");
    }

    public String getBrewery() {
        return getString("brewery");
    }

    public Number getABV() {
        return getNumber("abv");
    }
}
