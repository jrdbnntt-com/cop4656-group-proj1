package cop4656.jrdbnntt.com.groupproject1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allclasses);
        ListView listContent = (ListView) findViewById(R.id.contentlist);
        String[] from = new String[]{MyContentProvider.COLUMN_CLASS, MyContentProvider.COLUMN_ROOM,
                MyContentProvider.COLUMN_START, MyContentProvider.COLUMN_END, MyContentProvider.COLUMN_DAYS};
        int[] to = new int[]{R.id.text, R.id.text2, R.id.text3, R.id.text4, R.id.text5};
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);
        listContent.setAdapter(cursorAdapter);
    }
}
