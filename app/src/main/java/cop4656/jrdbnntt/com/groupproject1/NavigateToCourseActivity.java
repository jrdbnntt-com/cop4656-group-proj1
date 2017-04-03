package cop4656.jrdbnntt.com.groupproject1;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import cop4656.jrdbnntt.com.groupproject1.provider.MyContentProvider;
import cop4656.jrdbnntt.com.groupproject1.provider.table.Course;

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
        return null;
    }
}

