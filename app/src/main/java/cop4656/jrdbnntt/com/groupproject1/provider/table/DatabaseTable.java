package cop4656.jrdbnntt.com.groupproject1.provider.table;


/**
 * TODO
 */
public abstract class DatabaseTable {
    public static final String COLUMN_ID = "_id";

    private Long id;

    public abstract String getTableName();
    public abstract String getCreateSql();

    public Long getId() {
        if (id == null) {
            throw new NullPointerException("Missing id");
        }
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
