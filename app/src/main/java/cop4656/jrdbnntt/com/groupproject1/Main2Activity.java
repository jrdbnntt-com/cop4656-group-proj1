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
import android.widget.ArrayAdapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


import cop4656.jrdbnntt.com.groupproject1.provider.MyContentProvider;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allclasses);
        final ListView listContent = (ListView) findViewById(R.id.contentlist);
        String[] from = new String[]{MyContentProvider.COLUMN_CLASS, MyContentProvider.COLUMN_ROOM,
                MyContentProvider.COLUMN_START, MyContentProvider.COLUMN_END, MyContentProvider.COLUMN_DAYS};
        int[] to = new int[]{R.id.text, R.id.text2, R.id.text3, R.id.text4, R.id.text5};
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);
        listContent.setAdapter(cursorAdapter);
        listContent.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                Cursor cursor1 = (Cursor) listContent.getItemAtPosition(myItemInt);
                final String class1 = cursor1.getString(cursor1.getColumnIndexOrThrow(MyContentProvider.COLUMN_CLASS));
                final String startTime = cursor1.getString(cursor1.getColumnIndexOrThrow(MyContentProvider.COLUMN_START));
                final String build = cursor1.getString(cursor1.getColumnIndexOrThrow(MyContentProvider.COLUMN_ROOM));
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
                                Intent intent = new Intent(Main2Activity.this, navActiv.class);
                                intent.putExtra("class", class1);
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
