package com.example.samplemylocationwidget

import android.app.PendingIntent
import android.app.Service
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.location.*
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews

const val TAG = "GPSLocationService"
var ycoord = 0.0
var xcoord = 0.0

class MyLocationProvider : AppWidgetProvider() {
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

            val uri = "geo: $ycoord, $xcoord?z=10"  // 지도를 띄우기 위한 URI 문자열 생성(z: 지도의 확대/축소 값)
            val intent = Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)) // 지도를 띄우기 위한 인텐트 객체 생성

            // 텍스트 뷰를 눌렀을 때 내 위치 좌표를 이용해 지도를 띄워주기 위해 설정하는 인텐트는 미리 설정되어야 하므로 PendingIntent 객체로 설정
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            val views = RemoteViews(context?.packageName, R.layout.mylocation)
            views.setOnClickPendingIntent(R.id.txtInfo, pendingIntent)  // 뷰를 눌렀을 때 실행할 펜딩 인텐트 객체 지정
            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }

        context?.startService(Intent(context, GPSLocationService::class.java)) //8
    }

    inner class GPSLocationService : Service() {
        private var manager: LocationManager? = null
        private val listener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                Log.d(TAG, "onLocationChanged() called.")
                updateCoordinates(location?.latitude, location?.longitude)
                stopSelf()
            }
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String?) {}
            override fun onProviderDisabled(provider: String?) {}
        }

        override fun onBind(intent: Intent?): IBinder? {
            return null
        }

        override fun onCreate() {
            super.onCreate()

            Log.d(TAG, "onCreate() called.")

            manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            return super.onStartCommand(intent, flags, startId)

            startListening()
        }

        override fun onDestroy() {
            stopListening()

            Log.d(TAG, "onDestroy() called.")

            super.onDestroy()
        }

        private fun startListening() {
            Log.d(TAG, "startListening() called.")

            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_COARSE
            criteria.isAltitudeRequired = false
            criteria.isBearingRequired = false
            criteria.isCostAllowed = true
            criteria.powerRequirement = Criteria.POWER_LOW

            val bestProvider = manager?.getBestProvider(criteria, true)
            if (bestProvider != null && bestProvider.length > 0) {
                manager.requestLocationUpdates(bestProvider, 500, 10f, listener)
            } else {
                val providers = manager?.getProviders(true)

                for (provider in providers!!) {
                    manager.requestLocationUpdates(provider, 500, 10f, listener)
                }
            }
        }

        private fun stopListening() {
            if (manager != null && listener != null) {
                manager.removeUpdates(listener)
            }
            manager = null
        }

        private fun updateCoordinates(latitude: Double, longitude: Double) {
            var coder: Geocoder? = Geocoder(this)
            var addresses: List<Address>? = null
            var info = ""

            Log.d(TAG, "updateCoordinates() called.")

            addresses = coder.getFromLocation(latitude, longitude, 2)

            if (addresses != null && addresses.size > 0) {
                val addressCount = addresses[0].maxAddressLineIndex

                if (addressCount != -1) {
                    for (index in 0..addressCount) {
                        info += addresses[0].getAddressLine(index)

                        if (index < addressCount)
                            info += ", "
                    }
                } else {
                    info += addresses[0].featureName + ", " +
                            addresses[0].subAdminArea + ", " +
                            addresses[0].adminArea
                }
            }

            coder = null
            addresses = null

            if (info.length <= 0) {
                info = "[내 위치] $latitude, $longitude \n터치하면 지도로 볼 수 있습니다."
            } else {
                info += "\n[내 위치] $latitude, $longitude)"
                info += "\n터치하면 지도로 볼 수 잇습니다."
            }

            val views = RemoteViews(packageName, R.layout.mylocation)

            views.setTextViewText(R.id.txtInfo, info)

            val thisWidget = ComponentName(this, MyLocationProvider::class.java)
            val manager = AppWidgetManager.getInstance(this)
            manager.updateAppWidget(thisWidget, views)

            xcoord = longitude
            ycoord = latitude
        }
    }
}