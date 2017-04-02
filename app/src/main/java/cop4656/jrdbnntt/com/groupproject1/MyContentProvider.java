package cop4656.jrdbnntt.com.groupproject1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.UriMatcher;
import android.util.Log;

public class MyContentProvider extends ContentProvider {

    MainDatabaseHelper helper1;
    public static final String DBNAME = "ScheduleDB1";
    public static final String TABLE_NAME = "Classes";
    public static final String KEY_ID = "_id";
    public static final String COLUMN_CLASS = "class";
    public static final String COLUMN_ROOM = "room";
    public static final String COLUMN_START = "start";
    public static final String COLUMN_END = "end";
    public static final String COLUMN_DAYS = "days";
    private static final String SQL_CREATE_MAIN = "CREATE TABLE " + TABLE_NAME + " (" + " _id INTEGER PRIMARY KEY, "
            + COLUMN_CLASS + " TEXT," + COLUMN_ROOM + " TEXT," + COLUMN_START + " TEXT," + COLUMN_END + " TEXT,"
            + COLUMN_DAYS + " TEXT)";
    public static final String AUTHORITY = "cop4656.jrdbnntt.com.groupproject1.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

    @Override
    public boolean onCreate()
    {
        helper1 = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public int delete(Uri uri, String whereClause, String[] whereArgs)
    {
        return helper1.getWritableDatabase().delete(TABLE_NAME, whereClause, whereArgs);
    }

    @Override
    public String getType(Uri uri)
    {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        String class1 = values.getAsString(COLUMN_CLASS).trim();
        String room = values.getAsString(COLUMN_ROOM).trim();
        String start = values.getAsString(COLUMN_START).trim();
        String end = values.getAsString(COLUMN_END).trim();
        String days = values.getAsString(COLUMN_DAYS).trim();

        if(class1.equals(""))
            return null;
        if(room.equals(""))
            return null;
        if(start.equals(""))
            return null;
        if(end.equals(""))
            return null;
        if(days.equals(""))
            return null;

        long id = helper1.getWritableDatabase().insert(TABLE_NAME, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }


    @Override
    public Cursor query(Uri uri, String[] columns, String selection, String[] args, String orderBy)
    {
        return helper1.getReadableDatabase().query(TABLE_NAME, columns, selection, args, null, null, orderBy);
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        return helper1.getWritableDatabase().update(TABLE_NAME, values, selection, selectionArgs);
    }

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper
    {
        MainDatabaseHelper(Context context)
        {
            super(context, "ScheduleDB1", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(SQL_CREATE_MAIN);
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
        {

        }
    }
}
