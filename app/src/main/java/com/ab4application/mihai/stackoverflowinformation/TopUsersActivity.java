package com.ab4application.mihai.stackoverflowinformation;

/**
* This is the main activity
 * */

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class TopUsersActivity extends AppCompatActivity {

    private RecyclerView rv;
    private LinearLayoutManager layoutManager;
    private static List<Developer> devMainList;
    private static String devUrl =
            "https://api.stackexchange.com/2.2/users?order=desc&sort=reputation&site=stackoverflow&key=7IMNkELcDMRrfan6Kd7k3w((";
    private static int noOfUsersToDisplay = 10;
    private static List<Bitmap> bitmapList;


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
        devMainList = new ArrayList<>();
        RVAdapter adapter = new RVAdapter(devMainList);
        rv.setAdapter(adapter);

        new GetDevelopers().execute();

    }

    private void initializeData() {
        devMainList = new ArrayList<>();
        devMainList.add( new Developer("Mihai Tataru", "Manchester, UK", null,
                5, 24, 45) );
        devMainList.add( new Developer("John Smith", "London, UK", null,
                0, 2, 42) );
        devMainList.add( new Developer("Alan Gilbert", "Bucharest, ROM", null,
                2, 12, 41) );
        devMainList.add( new Developer("Mihai Tataru", "Manchester, UK", null,
                5, 24, 45) );
        devMainList.add( new Developer("John Smith", "London, UK", null,
                0, 2, 42) );
        devMainList.add( new Developer("Alan Gilbert", "Bucharest, ROM", null,
                2, 12, 41) );
        devMainList.add( new Developer("Mihai Tataru", "Manchester, UK", null,
                5, 24, 45) );
        devMainList.add( new Developer("John Smith", "London, UK", null,
                0, 2, 42) );
        devMainList.add( new Developer("Alan Gilbert", "Bucharest, ROM", null,
                2, 12, 41) );
    }

    public static Developer getDeveloper(int index)
    {
        return devMainList.get(index);
    }

    public static Bitmap getBitmap(int index)
    {
        return bitmapList.get(index);
    }

    // AsyncTask to load bitmaps
    private class DownloadImageTask extends AsyncTask<String, Void, List<Bitmap> > {

        public DownloadImageTask() {
        }

        protected List<Bitmap> doInBackground(String... urls) {
            String urldisplay = urls[0];
            List<Bitmap> photos = new ArrayList<>();
            Bitmap devPhoto = null;
            for(int index = 0; index < urls.length; index++) {
                urldisplay = urls[index];
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    devPhoto = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                photos.add(devPhoto);
            }
            return photos;
        }

        protected void onPostExecute(List<Bitmap> result) {
            RVAdapter adapter = new RVAdapter(devMainList, result);
            rv.setAdapter(adapter);
            bitmapList = result;
        }
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

        private File createFile() throws IOException {
            File outputDir = getApplicationContext().getCacheDir();
            return new File(outputDir, "jsonFile.json");
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            String jsonStr = "";
            long expireTime = 1000 * 60 * 30; // 30 minutes in miliseconds
            // Checking for existing temp file
            File f = null;
            if(getApplicationContext().getCacheDir().listFiles().length > 0) {
                f = getApplicationContext().getCacheDir().listFiles()[0];

                if (Calendar.getInstance().getTimeInMillis() - f.lastModified() < expireTime) {

                    // Reading from the file
                    try {
                        int length = (int) f.length();

                        byte[] bytes = new byte[length];

                        FileInputStream in = new FileInputStream(f);
                        try {
                            in.read(bytes);
                        } finally {
                            in.close();
                        }

                        jsonStr = new String(bytes);
                    } catch (IOException ex) {
                        Log.d("Exception: ", ex.getMessage());
                    }
                }
                else {
                    // If the file is older than expireTime
                    // Delete the file and create a new one
                    try {
                        f.delete();
                        // Writing to the file
                        f = createFile();
                        // Creating service handler class instance
                        HttpRequests webreq = new HttpRequests();

                        // Making a request to url and getting response

                        jsonStr = webreq.makeWebServiceCall(devUrl, HttpRequests.GETRequest);
                        Log.d("Response: ", "" + jsonStr);

                        FileOutputStream stream = new FileOutputStream(f);
                        try {
                            stream.write(jsonStr.getBytes());
                        } finally {
                            stream.close();
                        }
                    }
                    catch(IOException ex) {
                        Log.d("Exception: ", ex.getMessage());
                    }
                }
            }
            else {
                try {
                    // Writing to the file
                    f = createFile();
                    // Creating service handler class instance
                    HttpRequests webreq = new HttpRequests();

                    // Making a request to url and getting response

                    jsonStr = webreq.makeWebServiceCall(devUrl, HttpRequests.GETRequest);
                    Log.d("Response: ", "" + jsonStr);

                    FileOutputStream stream = new FileOutputStream(f);
                    try {
                        stream.write(jsonStr.getBytes());
                    } finally {
                        stream.close();
                    }
                }
                catch(IOException ex) {
                    Log.d("Exception: ", ex.getMessage());
                }
            }


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
                devMainList = convertedList;

                new DownloadImageTask().execute(Developer.getUrls(devMainList));
                
            }
        }
    }
}
