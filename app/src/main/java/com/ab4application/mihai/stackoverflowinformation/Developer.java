package com.ab4application.mihai.stackoverflowinformation;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mihai on 22/03/2018.
 */

public class Developer {
    String name;
    String location;
    Bitmap photo;
    int goldBadges;
    int silverBadges;
    int bronzeBadges;

    public Developer(String name, String location, String photoUrl, int goldBadges,
                     int silverBadges, int bronzeBadges) {
        this.name = name;
        this.location = location;

        this.goldBadges = goldBadges;
        this.silverBadges = silverBadges;
        this.bronzeBadges = bronzeBadges;
    }

    

    public static ArrayList<Developer> convertList(ArrayList<HashMap<String, String>> devList) {
        ArrayList<Developer> list = new ArrayList<>();

        for(int index = 0; index < devList.size(); index++) {
            Developer dev = new Developer(devList.get(index).get(JsonParser.TAG_NAME),
                    devList.get(index).get(JsonParser.TAG_LOCATION),
                    devList.get(index).get(JsonParser.TAG_PROFILE_IMAGE),
                    Integer.parseInt( devList.get(index).get(JsonParser.TAG_BADGE_GOLD) ),
                    Integer.parseInt( devList.get(index).get(JsonParser.TAG_BADGE_SILVER) ),
                    Integer.parseInt( devList.get(index).get(JsonParser.TAG_BADGE_BRONZE) ) );
            list.add(dev);
        }

        return list;
    }
}
