package cop4656.jrdbnntt.com.groupproject1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/*
    Creates SharedPreferences "my_prefs". Two datapoints: "preferred_garage" and "alarm_delay".
    "preferred_garage" gives the name of the preferred garage, minus the word "garage", as a string.
    "alarm_delay" gives the time in minutes, as an int, that the alarm should be displayed before
    the next class. Ex. if a class was to start at 10:30 AM, and the preference was set to 5 minutes,
    the alarm should display at 10:25. As such, int value 5 is 5 minutes, 30 is 30 minutes, 60 is
    1 hour, and so on.
 */

public class AppPreferences extends AppCompatActivity {
    String timeArray[] = {"5 minutes", "10 minutes", "15 minutes", "30 minutes", "1 hour"};
    String garageArray[] = {"Woodward","Call Street", "Traditions", "St Augustine", "Pensacola St",
        "Spirit Way"};
    ArrayAdapter<String> adapterGarageArray, adapterTimeArray;
    Spinner garageSpinner, alarmSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_preferences);

        garageSpinner = (Spinner) findViewById(R.id.parkingSpinner);
        alarmSpinner = (Spinner) findViewById(R.id.alarmSpinner);

        adapterGarageArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, garageArray);
        adapterTimeArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timeArray);

        garageSpinner.setAdapter(adapterGarageArray);
        alarmSpinner.setAdapter(adapterTimeArray);
    }

    public int alarmInt(String s) {
        for (int i = 0; i < timeArray.length; i++)
        {
            if (s.equals(timeArray[i]))
                return i;
        }
        return -1;
    }

    public int alarmRealTime(int i) {
        switch(i) {
            case 0: return 5;
            case 1: return 10;
            case 2: return 15;
            case 3: return 30;
            case 4: return 60;
        }
        return 0;
    }


    public void submit(View view) {
        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String alarmSelectText = alarmSpinner.getSelectedItem().toString().trim();
        String garageSelectText = garageSpinner.getSelectedItem().toString().trim();

        editor.putString("preferred_garage", garageSelectText);
        editor.putInt("alarm_delay", alarmRealTime(alarmInt(alarmSelectText)));
        boolean committed = editor.commit();

        if (committed) {
            Toast.makeText(this, "Data Received! Alert user " + alarmRealTime(alarmInt(alarmSelectText)) +
                    " minutes before class and park at " + garageSelectText
                    + " Garage.", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "ERR: DATA NOT WRITTEN.",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.iAddCourse:
                i = new Intent(this, AddNewCourseActivity.class);
                startActivity(i);
                break;
            case R.id.iViewSchedule:
                i = new Intent(this, CoursesListActivity.class);
                startActivity(i);
                break;
        }

        return true;
    }
}
