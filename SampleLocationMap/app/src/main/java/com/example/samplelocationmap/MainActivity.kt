package com.example.samplelocationmap

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class MainActivity : AppCompatActivity() {
    lateinit var mapFragment: SupportMapFragment
    lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync {
            map = it
        }

        MapsInitializer.initialize(this)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            startLocationService()
        }
    }

    private fun startLocationService() {
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val gpsListener = GPSListener()
        val minTime = 10000L
        val minDistance = 0F

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener)
        Toast.makeText(this, "내 위치확인 요청함", Toast.LENGTH_SHORT).show()
    }

    inner class GPSListener : LocationListener {
        override fun onLocationChanged(location: Location?) {   // 위치가 확인되었을 때 자동으로 호출됨
            val latitude = location!!.latitude
            val longitude = location.longitude

            showCurrentLocation(latitude, longitude)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        override fun onProviderEnabled(provider: String?) {}
        override fun onProviderDisabled(provider: String?) {}
    }

    private fun showCurrentLocation(latitude: Double, longitude: Double) {
        val curPoint = LatLng(latitude, longitude)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15f))
    }
}
