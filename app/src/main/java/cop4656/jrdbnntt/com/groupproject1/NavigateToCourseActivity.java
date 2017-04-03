package cop4656.jrdbnntt.com.groupproject1;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cop4656.jrdbnntt.com.groupproject1.provider.MyContentProvider;
import cop4656.jrdbnntt.com.groupproject1.provider.table.Course;
import cop4656.jrdbnntt.com.groupproject1.provider.types.Time;

public class NavigateToCourseActivity extends AppCompatActivity {
    TextView courseN, roomN, startT, travelT;
    Course course;

    private static String ARG_COURSE_ID = "courseId";


    public static Intent newNavigateIntent(Context context, long courseId) {
        Intent intent = new Intent();
        intent.setClass(context, NavigateToCourseActivity.class);
        intent.putExtra(ARG_COURSE_ID, courseId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate_to_course);
        Bundle extras = getIntent().getExtras();
        courseN = (TextView) findViewById(R.id.cN);
        roomN = (TextView) findViewById(R.id.rN);
        startT = (TextView) findViewById(R.id.startTime);
        travelT = (TextView) findViewById(R.id.insertText);

        course = getCourseById(extras.getLong(ARG_COURSE_ID));

        if(course != null) {
            courseN.setText(course.name);
            roomN.setText(course.room);
            startT.setText(course.startTime.toString());
        }

        class DirectionsTask extends AsyncTask<Void, Void, String> {
            private Exception exception;

            protected void onPreExecute() {
                travelT.setText("");
            }

            protected String doInBackground(Void... urls) {

                try {
                    URL url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=Lucky+Goat+Coffee,+1307+N+Monroe+St+%235,+Tallahassee,+FL+32303&destination=MCH+Tallahassee,+FL");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();
                        return stringBuilder.toString();
                    }
                    finally{
                        urlConnection.disconnect();
                    }
                } catch(Exception e) {
                    Log.e("ERROR", e.getMessage(), e);
                    return null;
                }
            }

            protected void onPostExecute(String response) {
                if(response == null) {
                    response = "ERROR";
                }

                try {
                    JSONObject directions = new JSONObject(response);
                    travelT.setText(directions
                    .getJSONArray("routes")
                    .getJSONObject(0)
                    .getJSONArray("legs")
                    .getJSONObject(0)
                    .getJSONObject("duration")
                    .getString("text"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        new DirectionsTask().execute();
    }

    public void navButton(View view)
    {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=Milton+Carothers+Hall+FSU");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
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

    public Course getCourseById(long courseId) {
        // TODO implement
        Course temp = new Course();
        String selection = "_id = ?";
        String[] args = {Long.toString(courseId)};
        Cursor cursor = getContentResolver().query(
                MyContentProvider.getUriForTable(Course.TABLE_NAME),
                null,
                selection,
                args,
                null
        );

        if(cursor.getCount() == 1) {
            cursor.moveToFirst();
            temp.name = cursor.getString(1);
            try {
                temp.startTime = new Time(cursor.getString(3));
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage());
                return null;
            }

            temp.room = cursor.getString(2);
            return temp;
        }

        return null;
    }
}

