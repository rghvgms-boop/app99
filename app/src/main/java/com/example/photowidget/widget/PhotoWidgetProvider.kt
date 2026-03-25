package com.example.photowidget.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.work.*
import com.example.photowidget.R
import java.util.concurrent.TimeUnit

class PhotoWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Schedule periodic updates when the widget is updated
        schedulePeriodicUpdate(context)
        
        // Update each widget
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Schedule periodic updates when the first widget is added
        schedulePeriodicUpdate(context)
    }

    override fun onDisabled(context: Context) {
        // Cancel updates when the last widget is removed
        WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == ACTION_REFRESH) {
            // Trigger immediate update
            val workRequest = OneTimeWorkRequestBuilder<PhotoWidgetWorker>()
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                .build()
            WorkManager.getInstance(context).enqueue(workRequest)
        }
    }

    companion object {
        private const val WORK_NAME = "PhotoWidgetUpdateWork"
        const val ACTION_REFRESH = "com.example.photowidget.ACTION_REFRESH"

        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int) {
            
            val views = RemoteViews(context.packageName, R.layout.photo_widget_layout)
            
            // Set up refresh button intent
            val intent = Intent(context, PhotoWidgetProvider::class.java).apply {
                action = ACTION_REFRESH
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context, 0, intent, 
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.refresh_button, pendingIntent)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
            
            // Trigger an immediate worker run to load the image
            val workRequest = OneTimeWorkRequestBuilder<PhotoWidgetWorker>().build()
            WorkManager.getInstance(context).enqueue(workRequest)
        }

        private fun schedulePeriodicUpdate(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val periodicWorkRequest = PeriodicWorkRequestBuilder<PhotoWidgetWorker>(
                1, TimeUnit.HOURS
            )
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest
            )
        }
    }
}
