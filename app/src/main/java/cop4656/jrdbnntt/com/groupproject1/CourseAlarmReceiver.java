package cop4656.jrdbnntt.com.groupproject1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * TODO
 */
public class CourseAlarmReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 124462;

    @Override
    public void onReceive(Context context, Intent intent) {

        // Get course id from intent

        // Get course name + location information from DB (using course id)

        // Estimate navigation time in minutes

        // Display notification time
    }

    private void displayNotification(String courseName, int minutes) {

    }
}
