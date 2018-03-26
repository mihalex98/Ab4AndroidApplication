package com.ab4application.mihai.stackoverflowinformation;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mihai on 26/03/2018.
 */

public class JsonParser {

    public static final String TAG_ITEMS = "items";
    public static final String TAG_BADGE_COUNTS = "badge_counts";
    public static final String TAG_BADGE_BRONZE = "bronze";
    public static final String TAG_BADGE_SILVER = "silver";
    public static final String TAG_BADGE_GOLD = "gold";
    public static final String TAG_LOCATION = "location";
    public static final String TAG_NAME = "display_name";
    public static final String TAG_PROFILE_IMAGE = "profile_image";


    public JsonParser() {
    }

    public static ArrayList<HashMap<String, String>> parseJson(String json, int noOfItems) {
        if (json != null) {
            try {
                // create the array list of hashmaps to remember each developer
                ArrayList<HashMap<String, String>> devList = new ArrayList<>();
                JSONObject jsonObj = new JSONObject(json);

                // display the first noOfItems devs / all of them if there are less then requested
                JSONArray developers = jsonObj.getJSONArray(TAG_ITEMS);
                if(noOfItems > developers.length())
                    noOfItems = developers.length();


                for (int i = 0; i < noOfItems; i++) {
                    JSONObject c = developers.getJSONObject(i);

                    String location = c.getString(TAG_LOCATION);
                    String name = c.getString(TAG_NAME);
                    String profileImage = c.getString(TAG_PROFILE_IMAGE);

                    JSONObject badges = c.getJSONObject(TAG_BADGE_COUNTS);
                    String bronze = badges.getString(TAG_BADGE_BRONZE);
                    String silver = badges.getString(TAG_BADGE_SILVER);
                    String gold = badges.getString(TAG_BADGE_GOLD);

                    HashMap<String, String> developer = new HashMap<>();

                    developer.put(TAG_NAME, name);
                    developer.put(TAG_LOCATION, location);
                    developer.put(TAG_PROFILE_IMAGE, profileImage);
                    developer.put(TAG_BADGE_BRONZE, bronze);
                    developer.put(TAG_BADGE_SILVER, silver);
                    developer.put(TAG_BADGE_GOLD, gold);

                    devList.add(developer);
                }
                return devList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("ServiceHandler", "No data received from HTTP request");
            return null;
        }
    }
}
