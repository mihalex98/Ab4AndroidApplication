package com.ab4application.mihai.stackoverflowinformation;

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
}
