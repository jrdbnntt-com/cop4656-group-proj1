package cop4656.jrdbnntt.com.groupproject1.provider.table;

import java.util.Date;


public class Course implements DatabaseTable {
    public static final String TABLE_NAME = "course";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ROOM = "room";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_DAYS = "days";

    public Integer id;
    public String name;
    public String room;
    public Date start_time;
    public String days;

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

    public void refreshAlarms() {
        // TODO
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
