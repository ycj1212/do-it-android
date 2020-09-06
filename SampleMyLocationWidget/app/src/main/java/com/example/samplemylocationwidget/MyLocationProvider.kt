package com.example.samplemylocationwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.RemoteViews

var ycoord = 0.0
var xcoord = 0.0

class MyLocationProvider : AppWidgetProvider {
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.d("MyLocationProvider", "onUpdate() called : $ycoord, $xcoord")

        val size = appWidgetIds?.size!!

        for (i in 0 until size) {
            val appWidgetId = appWidgetIds[i]

            val uri = "geo: $ycoord, $xcoord?z=10"

            val intent = Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri))
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            val views = RemoteViews(context?.packageName, R.layout.mylocation)
            views.setOnClickPendingIntent(R.id.txtInfo, pendingIntent)
            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }

        context.startService(Intent(context, GPSLocationService.class))
    }
}