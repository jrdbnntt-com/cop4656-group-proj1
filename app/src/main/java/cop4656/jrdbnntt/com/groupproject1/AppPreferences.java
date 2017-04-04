package cop4656.jrdbnntt.com.groupproject1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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

    public void submit(View view) {
        String alarmSelectText = alarmSpinner.getSelectedItem().toString();
        String garageSelectText = garageSpinner.getSelectedItem().toString();
        Toast.makeText(this,"Data Received! Alert user " + alarmSelectText +
                " before class and park at " + garageSelectText + " Garage.",Toast.LENGTH_LONG).show();
    }
}
