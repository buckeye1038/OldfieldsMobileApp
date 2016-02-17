package com.havvyapps.oldfields_new;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Class to handle Food objects
 */
@ParseClassName("Food")
public class Food extends ParseObject {

    public Food() {
        // A default constructor is required.
    }

    public String getName() {
        return getString("name");
    }

    public String getDescription() {
        return getString("description");
    }

    public Number getPrice() {
        return getDouble("price");
    }

    public Boolean getActive() {
        return getBoolean("active");
    }
}
