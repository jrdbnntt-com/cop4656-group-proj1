package cop4656.jrdbnntt.com.groupproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.app.AlertDialog;
import android.content.DialogInterface;


import cop4656.jrdbnntt.com.groupproject1.provider.MyContentProvider;
import cop4656.jrdbnntt.com.groupproject1.provider.table.Course;

/**
 * List view of courses in database. Opens a dialog to on click to choose to navigate or edit the
 * course.
 */
public class ViewAllCoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allclasses);

        Cursor cursor = getContentResolver().query(
                MyContentProvider.getUriForTable(Course.TABLE_NAME), null, null, null, null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, R.layout.row, cursor,
                new String[] {
                        Course.COLUMN_NAME,
                        Course.COLUMN_ROOM,
                        Course.COLUMN_START_TIME,
                        Course.COLUMN_DAYS
                },
                new int[] {
                        R.id.tvCourseName,
                        R.id.tvRoom,
                        R.id.tvStartTime,
                        R.id.tvDays
                }
        );

        final ListView listContent = (ListView) findViewById(R.id.contentlist);
        listContent.setAdapter(adapter);

        listContent.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                Cursor cursor1 = (Cursor) listContent.getItemAtPosition(myItemInt);


                final Course course = new Course();

                course.name = cursor1.getString(cursor1.getColumnIndexOrThrow(Course.COLUMN_NAME));
                course.start_time = Datecursor1.getString(cursor1.getColumnIndexOrThrow(Course.COLUMN_START_TIME));

//                final String courseName =
//                final String startTime =
//                final String build = cursor1.getString(cursor1.getColumnIndexOrThrow(MyContentProvider.COLUMN_ROOM));

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Course info");
                alertDialogBuilder
                        .setMessage("Click edit to edit the course, click navigate to bring up navigation info.")
                        .setCancelable(false)
                        .setPositiveButton("Edit",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //delete course then bring up add class dialog
                            }
                        })
                        .setNegativeButton("Navigate",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Intent intent = new Intent(ViewAllCoursesActivity.this, NavigateToCourseActivity.class);
                                intent.putExtra("class", courseName);
                                intent.putExtra("start", startTime);
                                intent.putExtra("room", build);
                                startActivity(intent);
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });


    }
}
