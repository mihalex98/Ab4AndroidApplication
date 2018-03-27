package com.ab4application.mihai.stackoverflowinformation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        // getting the developer
        Intent intent = getIntent();
        int devId = intent.getIntExtra("dev", 0);
        Developer dev = TopUsersActivity.getDeveloper(devId);

        //initalize the views
        TextView nameView = findViewById(R.id.devName);
        nameView.setText(dev.name);

        TextView locView = findViewById(R.id.textLocation);
        locView.setText(dev.location);

        TextView goldView = findViewById(R.id.textGold);
        goldView.setText(String.valueOf(dev.goldBadges));
        TextView silverView = findViewById(R.id.textSilver);
        silverView.setText(String.valueOf(dev.silverBadges));
        TextView bronzeView = findViewById(R.id.textBronze);
        bronzeView.setText(String.valueOf(dev.bronzeBadges));

        ImageView profile = findViewById(R.id.devImage);
        profile.setImageBitmap(TopUsersActivity.getBitmap(devId));

    }

}
