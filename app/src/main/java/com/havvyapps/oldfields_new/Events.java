package com.havvyapps.oldfields_new;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Class to handle Food objects
 */
@ParseClassName("Event")
public class Events extends ParseObject {

    public Events() {
        // A default constructor is required.
    }

    public String getName() {
        return getString("name");
    }

    public String getDescription() {
        return getString("description");
    }

    public Date getDate() {
        return getDate("date");
    }
}
