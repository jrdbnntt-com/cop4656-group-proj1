package cop4656.jrdbnntt.com.groupproject1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * TODO
 */
public class CourseAlarmReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 124462;
    private static final int ALARM_INTENT_ID = 23415;
    private static final String ARG_COURSE_ID = "courseId";

    public static Intent newAlarmIntent(Context context, long courseId) {
        Intent intent = new Intent(context.getString(R.string.action_ALARM));
        intent.putExtra(ARG_COURSE_ID, courseId);
        return intent;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        // Get course id from intent

        // Get course name + location information from DB (using course id)

        // Estimate navigation time in minutes

        // Display notification time

        Bundle bundle = intent.getExtras();
        Log.d("ALARM_TRIGGERED", "id = " + bundle.getLong(ARG_COURSE_ID));
    }

    private void displayNotification(String courseName, int minutes) {

    }
}
