package cop4656.jrdbnntt.com.groupproject1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class NavigateToCourseActivity extends AppCompatActivity {
    TextView courseN, roomN, startT;

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
        String extra1 = extras.getString("class");
        String extra2 = extras.getString("start");
        String extra3 = extras.getString("room");

        courseN = (TextView) findViewById(R.id.cN);
        roomN = (TextView) findViewById(R.id.rN);
        startT = (TextView) findViewById(R.id.startTime);

        courseN.setText(extra1);
        startT.setText(extra2);
        roomN.setText(extra3);
    }

    public void navButton(View view)
    {
        //Intent intent = new Intent(this, NavigateToCourseActivity.class); change to the navigation activity, or google maps whatever
        //startActivity(intent);

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

