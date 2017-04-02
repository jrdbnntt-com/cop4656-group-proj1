package cop4656.jrdbnntt.com.groupproject1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Button;
import android.view.*;
import java.lang.StringBuilder;

import android.net.Uri;
import android.content.ContentValues;
import android.content.Intent;

import cop4656.jrdbnntt.com.groupproject1.provider.MyContentProvider;

public class MainActivity extends AppCompatActivity {
    EditText cNum, start1, end1, room1;
    CheckBox m, t, w, th, f;
    Button sub, sched;
    String course, begin, end2, roomN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclass);

        cNum = (EditText) findViewById(R.id.courseNum);
        start1 = (EditText) findViewById(R.id.startT);
        end1 = (EditText) findViewById(R.id.endT);
        room1 = (EditText) findViewById(R.id.roomNumT);
        m = (CheckBox) findViewById(R.id.mon);
        t = (CheckBox) findViewById(R.id.tues);
        w = (CheckBox) findViewById(R.id.wen);
        th = (CheckBox) findViewById(R.id.thur);
        f = (CheckBox) findViewById(R.id.fri);
        sub = (Button) findViewById(R.id.submit);
        sched = (Button) findViewById(R.id.button2);
    }

    public void submitClick(View view)
    {
        course = cNum.getText().toString();
        begin = start1.getText().toString();
        end2 = end1.getText().toString();
        roomN = room1.getText().toString();
        String days2;                            //days string
        StringBuilder str = new StringBuilder();
        if(m.isChecked())                       //check which days were checked
            str.append("M");
        if(t.isChecked())
            str.append("T");
        if(w.isChecked())
            str.append("W");
        if(th.isChecked())
            str.append("TH");
        if(f.isChecked())
            str.append("F");

        days2 = str.toString();
        Uri uri1;
        ContentValues values = new ContentValues();
        values.put(MyContentProvider.COLUMN_CLASS, course);
        values.put(MyContentProvider.COLUMN_ROOM, roomN);
        values.put(MyContentProvider.COLUMN_START, begin);
        values.put(MyContentProvider.COLUMN_END, end2);
        values.put(MyContentProvider.COLUMN_DAYS, days2);

        uri1 = getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
    }

    public void scheduleButton(View view)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}
