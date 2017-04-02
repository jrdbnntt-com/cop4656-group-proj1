package cop4656.jrdbnntt.com.groupproject1.provider.table;

public class CourseAlarm implements DatabaseTable {
    public static final String TABLE_NAME = "course_alarm";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COURSE_ID = "course_id";
    public static final String COLUMN_TRIGGER_TIME = "trigger_time";

    public Integer id;
    public Integer course_id;
    public Integer trigger_time;

    public CourseAlarm() {}

    @Override
    public String getCreateSql() {
        return "CREATE TABLE " + TABLE_NAME + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_COURSE_ID  + " INTEGER,"
                + COLUMN_TRIGGER_TIME + " INTEGER);";
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}