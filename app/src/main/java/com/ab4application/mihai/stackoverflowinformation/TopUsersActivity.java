package com.ab4application.mihai.stackoverflowinformation;

/**
* This is the main activity
 * */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TopUsersActivity extends AppCompatActivity {

    private RecyclerView rv;
    private LinearLayoutManager layoutManager;
    private List<Developer> devList;
    private static String devUrl =
            "https://api.stackexchange.com/2.2/users?order=desc&sort=reputation&site=stackoverflow";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_users);
        // get the recycleView
        rv = findViewById(R.id.recyclerView);

        // using layoutManager
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        // initializing data and passing it to the adapter
        initializeData();
        RVAdapter adapter = new RVAdapter(devList);
        rv.setAdapter(adapter);

    }

    private void initializeData() {
        devList = new ArrayList<>();
        devList.add( new Developer("Mihai Tataru", "Manchester, UK", 0,
                5, 24, 45) );
        devList.add( new Developer("John Smith", "London, UK", 0,
                0, 2, 42) );
        devList.add( new Developer("Alan Gilbert", "Bucharest, ROM", 0,
                2, 12, 41) );
        devList.add( new Developer("Mihai Tataru", "Manchester, UK", 0,
                5, 24, 45) );
        devList.add( new Developer("John Smith", "London, UK", 0,
                0, 2, 42) );
        devList.add( new Developer("Alan Gilbert", "Bucharest, ROM", 0,
                2, 12, 41) );
        devList.add( new Developer("Mihai Tataru", "Manchester, UK", 0,
                5, 24, 45) );
        devList.add( new Developer("John Smith", "London, UK", 0,
                0, 2, 42) );
        devList.add( new Developer("Alan Gilbert", "Bucharest, ROM", 0,
                2, 12, 41) );
    }
}
