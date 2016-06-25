package zaafranigabriel.recipy.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.ListView;
import android.widget.RemoteViews;

import java.util.Date;

import zaafranigabriel.recipy.ListValue.ListActivityWidget;
import zaafranigabriel.recipy.R;

/**
 * Created by HP 750-240nf on 23/06/2016.
 */
public class WidgetList extends AppWidgetProvider {

    private static final String TAG = "MyWidget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {


        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_list);
        ComponentName myWidget = new ComponentName(context, WidgetList.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);


    }
}
