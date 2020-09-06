package com.example.samplelocationmap

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pedro.library.AutoPermissions
import com.pedro.library.AutoPermissionsListener

class MainActivity : AppCompatActivity(), AutoPermissionsListener {
    lateinit var mapFragment: SupportMapFragment
    var map: GoogleMap? = null

    lateinit var myLocationMarker: MarkerOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("onCreate", "onCreate 호출됨")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync {
            map = it
            map?.isMyLocationEnabled = true
        }

        MapsInitializer.initialize(this)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            startLocationService()
        }

        AutoPermissions.Companion.loadAllPermissions(this, 101)
    }

    override fun onResume() {
        Log.d("onResume", "onResume 호출됨")
        super.onResume()

        if (map != null) {
            map?.isMyLocationEnabled = true
        }
    }

    override fun onPause() {
        Log.d("onPause", "onPause 호출됨")
        super.onPause()

        if (map != null) {
            map?.isMyLocationEnabled = false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions as Array<String>, this)
    }

    override fun onDenied(requestCode: Int, permissions: Array<String>) {
        Toast.makeText(this, "permissions denied : ${permissions.size}", Toast.LENGTH_LONG).show()
    }

    override fun onGranted(requestCode: Int, permissions: Array<String>) {
        Toast.makeText(this, "permissions granted : ${permissions.size}", Toast.LENGTH_LONG).show()
    }

    private fun startLocationService() {
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (location != null) {
            val latitude = location.latitude
            val longitude = location.longitude
            val message = "최근 위치 -> Latitude : $latitude \nLongitude : $longitude"

            Log.d("Map", message)
        }

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
            val message = "최근 위치 -> Latitude : $latitude \nLongitude : $longitude"

            Log.d("Map", message)

            showCurrentLocation(latitude, longitude)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        override fun onProviderEnabled(provider: String?) {}
        override fun onProviderDisabled(provider: String?) {}
    }

    private fun showCurrentLocation(latitude: Double, longitude: Double) {
        val curPoint = LatLng(latitude, longitude)
        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15f))

        showMyLocationMarker(curPoint)
    }

    private fun showMyLocationMarker(curPoint: LatLng) {
        myLocationMarker = MarkerOptions()
        myLocationMarker.position(curPoint)
        myLocationMarker.title("● 내 위치\n")
        myLocationMarker.snippet("● GPS로 확인한 위치")
        myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation))
        map?.addMarker(myLocationMarker)
    }
}
