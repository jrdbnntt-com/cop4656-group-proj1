package cop4656.jrdbnntt.com.groupproject1;

import android.content.Context;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

import cop4656.jrdbnntt.com.groupproject1.provider.MyContentProvider;
import cop4656.jrdbnntt.com.groupproject1.provider.table.Course;

/**
 * Created by Cristian Palencia on 4/4/2017.
 */

public class WidgetAdapter implements RemoteViewsService.RemoteViewsFactory {
    Context context;

    Cursor  cursor;

    String[] list = {"COP4530", "CIS4931", "COP4020"};// Insteado of using this list use the ContentProvider to find list

    ArrayList<String> stringList= new ArrayList<>();



    public WidgetAdapter(Context context)
    {
        this.context=context;


    }



    @Override
    public void onCreate() {
        cursor = context.getContentResolver().query(
                MyContentProvider.getUriForTable(Course.TABLE_NAME),
                new String [] {
                        Course.COLUMN_NAME
                },
                null,
                null,
                null
        );
        while(cursor.moveToFirst())
        {
            String course = cursor.getString(0);
            stringList.add(course);
        }




        list=stringList.toArray(new String[stringList.size()]);
        cursor.close();
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public RemoteViews getViewAt(int i) {



        RemoteViews rv= new RemoteViews(context.getPackageName(),R.layout.list_item);
        rv.setTextViewText(R.id.textView, list[i]);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
