package cop4656.jrdbnntt.com.groupproject1.provider.table;

import android.content.ContentValues;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import cop4656.jrdbnntt.com.groupproject1.provider.types.Time;
import cop4656.jrdbnntt.com.groupproject1.provider.types.WeekDayCollection;


public class Course extends DatabaseTable {

    // Database
    public static final String TABLE_NAME = "course";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ROOM = "room";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_DAYS = "days";

    // Column instance usage

    public String name;
    public String room;
    public Time startTime;
    public WeekDayCollection days;

    public Course() {}

    @Override
    public String getCreateSql() {
        return "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ROOM + " TEXT,"
                + COLUMN_START_TIME + " TEXT,"
                + COLUMN_DAYS + " TEXT);";
    }


    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

}
