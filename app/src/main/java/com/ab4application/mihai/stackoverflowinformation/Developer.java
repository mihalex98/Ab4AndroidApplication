package com.ab4application.mihai.stackoverflowinformation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mihai on 22/03/2018.
 */

public class Developer {
    String name;
    String location;
    int photoLocation;
    int goldBadges;
    int silverBadges;
    int bronzeBadges;

    public Developer(String name, String location, int photoLocation, int goldBadges,
                     int silverBadges, int bronzeBadges) {
        this.name = name;
        this.location = location;
        this.photoLocation = photoLocation;
        this.goldBadges = goldBadges;
        this.silverBadges = silverBadges;
        this.bronzeBadges = bronzeBadges;
    }

    public static ArrayList<Developer> convertList(ArrayList<HashMap<String, String>> devList) {
        ArrayList<Developer> list = new ArrayList<>();

        return list;
    }
}
