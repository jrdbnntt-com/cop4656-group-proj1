package cop4656.jrdbnntt.com.groupproject1.provider.table;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


public class Course implements DatabaseTable {
    public static final String TIME_FORMAT = "hh:mma";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());

    // Database
    public static final String TABLE_NAME = "course";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ROOM = "room";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_DAYS = "days";

    // Column instance usage
    private Integer id;
    private String name;
    private String room;
    private Calendar start_time;
    private String days;

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
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setStartTime(String startTime) throws ParseException {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(dateFormat.parse(startTime));
        this.start_time = calendar;
    }
    public Calendar getStartTime() {
        return start_time;
    }
    public String getStartTimeString() {
        return dateFormat.format(this.start_time.getTime());
    }

    public String getDays() {
        return days;
    }
    public void setDays(String days) {
        this.days = days;
    }

}
