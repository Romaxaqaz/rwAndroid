package by.client.android.railwayapp.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.page.scoreboard.TrainTypeToImage;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Implementation of App Widget functionality.
 *
 * @author RPA
 */
public class NewsWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Train train) {
        RemoteViews views = getView(context);

        if (train == null) {
            setVisibility(views, false);
        } else {
            setVisibility(views, true);
            updateWidgetView(views, train);
        }
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, null);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("TRAIN_ID")) {
                Train train = (Train) extras.getSerializable("TRAIN_ID");

                final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName name = new ComponentName(context, NewsWidget.class);
                int[] appWidgetId = AppWidgetManager.getInstance(context).getAppWidgetIds(name);

                updateAppWidget(context, appWidgetManager, appWidgetId[0], train);
            }
        }
    }

    private static void setVisibility(RemoteViews views, boolean isVisible) {
        if (isVisible) {
            views.setViewVisibility(R.id.trainInfo, VISIBLE);
            views.setViewVisibility(R.id.emptyView, GONE);
        } else {
            views.setViewVisibility(R.id.trainInfo, GONE);
            views.setViewVisibility(R.id.emptyView, VISIBLE);
        }
    }

    @Override
    public void onEnabled(Context context) {
        setVisibility(getView(context), false);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static void updateWidgetView(RemoteViews views, Train train) {
        views.setTextViewText(R.id.trainIdTextView, train.getId());
        views.setImageViewResource(R.id.icoImageView, new TrainTypeToImage().convert(train.getPathType()));
        views.setTextViewText(R.id.trainTypeTextView, train.getTrainType());
        views.setTextViewText(R.id.pathTextView, train.getPath());
        views.setTextViewText(R.id.wayTextView, train.getWay());
        views.setTextViewText(R.id.platformTextView, train.getPlatform());
        views.setTextViewText(R.id.startTimeTextView, train.getStart());
        views.setTextViewText(R.id.endTimeTextView, train.getEnd());
    }

    private static RemoteViews getView(Context context) {
        return new RemoteViews(context.getPackageName(), R.layout.news_widget);
    }
}

