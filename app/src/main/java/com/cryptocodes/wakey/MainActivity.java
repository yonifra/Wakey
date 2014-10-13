package com.cryptocodes.wakey;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;


public class MainActivity extends Activity {
    private Time time;
    Button buttonWake, buttonSave, buttonSleep;
    private static String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }

        time = new Time(Time.getCurrentTimezone());

        buttonWake = (Button) findViewById(R.id.buttonWake);
        buttonWake.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                ImageView img = (ImageView)findViewById(R.id.mainGraphicImage);
                img.setImageResource(R.drawable.eye46);
            }
        });

        buttonSleep = (Button) findViewById(R.id.buttonSleep);
        buttonSleep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                ImageView img = (ImageView)findViewById(R.id.mainGraphicImage);
                img.setImageResource(R.drawable.eye48);
            }
        });

        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Toast.makeText(getApplicationContext(), "Current time is: " + getCurrentTime(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getCurrentTime()
    {
        time.setToNow();
        StringBuilder sb = new StringBuilder();
        sb.append(time.monthDay);
        sb.append("/");
        sb.append(time.month);
        sb.append("/");
        sb.append(time.year);
        sb.append(" - ");
        sb.append(time.format("%k:%M:%S"));

        return sb.toString();
    }

    private void writeToFile(String data)
    {
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File (sdCard.getAbsolutePath() + "/");

        File file = new File(dir, "wakey.txt");

        try {
            FileOutputStream f = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {


        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
