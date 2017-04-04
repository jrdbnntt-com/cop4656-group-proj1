package cop4656.jrdbnntt.com.groupproject1;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

/**
 * Created by Cristian Palencia on 4/4/2017.
 */

public class WidgetAdapter implements RemoteViewsService.RemoteViewsFactory {
    Context context;

    String[] list = {"Algorithms", "Android", "Java", "Programming Languages", "Python"};// Insteado of using this list use the ContentProvider to find list


    public WidgetAdapter(Context context)
    {
        this.context=context;
    }

    @Override
    public void onCreate() {

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
