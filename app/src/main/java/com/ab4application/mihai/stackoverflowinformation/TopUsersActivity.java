package com.ab4application.mihai.stackoverflowinformation;

/**
* This is the main activity
 * */

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TopUsersActivity extends AppCompatActivity {

    private RecyclerView rv;
    private LinearLayoutManager layoutManager;
    private List<Developer> devTestList;
    private static String devUrl =
            "https://api.stackexchange.com/2.2/users?order=desc&sort=reputation&site=stackoverflow&key=7IMNkELcDMRrfan6Kd7k3w((";
    private static int noOfUsersToDisplay = 10;


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
        devTestList = new ArrayList<>();
        RVAdapter adapter = new RVAdapter(devTestList);
        rv.setAdapter(adapter);

        new GetDevelopers().execute();

    }

    private void initializeData() {
        devTestList = new ArrayList<>();
        devTestList.add( new Developer("Mihai Tataru", "Manchester, UK", null,
                5, 24, 45) );
        devTestList.add( new Developer("John Smith", "London, UK", null,
                0, 2, 42) );
        devTestList.add( new Developer("Alan Gilbert", "Bucharest, ROM", null,
                2, 12, 41) );
        devTestList.add( new Developer("Mihai Tataru", "Manchester, UK", null,
                5, 24, 45) );
        devTestList.add( new Developer("John Smith", "London, UK", null,
                0, 2, 42) );
        devTestList.add( new Developer("Alan Gilbert", "Bucharest, ROM", null,
                2, 12, 41) );
        devTestList.add( new Developer("Mihai Tataru", "Manchester, UK", null,
                5, 24, 45) );
        devTestList.add( new Developer("John Smith", "London, UK", null,
                0, 2, 42) );
        devTestList.add( new Developer("Alan Gilbert", "Bucharest, ROM", null,
                2, 12, 41) );
    }

    // AsyncTask to make http requests
    public class GetDevelopers extends AsyncTask<Void, Void, Void> {

        private ArrayList<HashMap<String, String>> devList;
        ProgressDialog proDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress loading dialog
            proDialog = new ProgressDialog(TopUsersActivity.this);
            proDialog.setMessage("Please wait...");
            proDialog.setCancelable(false);
            proDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            HttpRequests webreq = new HttpRequests();

            // Making a request to url and getting response
            String jsonStr = webreq.makeWebServiceCall(devUrl, HttpRequests.GETRequest);

            Log.d("Response: ", "" + jsonStr);


            devList = JsonParser.parseJson(jsonStr, noOfUsersToDisplay);

            return null;
        }

        @Override
        protected void onPostExecute(Void requestresult) {
            super.onPostExecute(requestresult);
            // Dismiss the progress dialog
            if (proDialog.isShowing())
                proDialog.dismiss();

            // Updating received data from JSON into ListView
            if(devList != null) {
                ArrayList<Developer> convertedList = Developer.convertList(devList);

                RVAdapter adapter = new RVAdapter(convertedList);
                rv.setAdapter(adapter);
            }
        }
    }
}
