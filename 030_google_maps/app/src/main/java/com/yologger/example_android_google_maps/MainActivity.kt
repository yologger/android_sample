package com.yologger.example_android_google_maps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
//
//    private val buttonZoomIn: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.activity_fab_zoom_in) }
//    private val buttonZoomOut: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.activity_fab_zoom_out) }
//    private val buttonCurrentLocation: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.activity_fab_current_location) }

    private var isPermissionsGranted = false

    companion object {
        const val REQUEST_CODE_PERMISSIONS = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // checkPermissions()

//        buttonZoomIn.setOnClickListener {
//            mMap?.moveCamera(CameraUpdateFactory.zoomIn())
//        }
//
//        buttonZoomOut.setOnClickListener {
//            mMap?.moveCamera(CameraUpdateFactory.zoomOut())
//        }
//
//        buttonCurrentLocation.setOnClickListener {
//            moveToCurrentPosition()
//        }
    }

    private fun checkPermissions() {

        val requiredPermissions = arrayOf<String>(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        )

        val rejectedPermissions = arrayListOf<String>()

        for (permission in requiredPermissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                rejectedPermissions.add(permission)
            }
        }

        if (rejectedPermissions.isNotEmpty()) {
            val array = arrayOfNulls<String>(rejectedPermissions.size)
            ActivityCompat.requestPermissions(this, rejectedPermissions.toArray(array), REQUEST_CODE_PERMISSIONS)
        }
    }

    private fun moveToCurrentPosition() {

        val hasFineLocationPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val hasCoarseLocationPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_CODE_PERMISSIONS)
        } else {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (isGPSEnable) {
                val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                location?.run {
                    val latitude = location.latitude
                    val longitude  = location.longitude
                    val myLocation = LatLng(latitude, longitude)
                    mMap?.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
                }

            } else if (isNetworkEnabled) {
                val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                location?.run {
                    val latitude = location.latitude
                    val longitude  = location.longitude
                    val myLocation = LatLng(latitude, longitude)
                    mMap?.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
                }
            } else {
            }
        }
    }


//    private fun updateLocationUI() {
//        if (mMap == null) {
//            return
//        }
//        try {
//            if (locationPermissionGranted) {
//                mMap.isMyLocationEnabled = true
//                mMap.uiSettings?.isMyLocationButtonEnabled = true
//            } else {
//                mMap.isMyLocationEnabled = false
//                mMap?.uiSettings?.isMyLocationButtonEnabled = false
//                getLocationPermission()
//            }
//        } catch (e: SecurityException) {
//
//        }
//    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val sydney = LatLng(-33.8688, 151.2093)
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        val hasFineLocationPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val hasCoarseLocationPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_CODE_PERMISSIONS)
        } else {
            mMap?.setMyLocationEnabled(true)

            val mapSettings = mMap?.uiSettings
            mapSettings?.run {
                setZoomControlsEnabled(true)
                setCompassEnabled(true)
                // setZoomGesturesEnabled(true)
            }
        }

//        val sydney = LatLng(-33.8688, 151.2093)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//
//        val seoul = LatLng(37.5665, 126.9780)
//        mMap.addMarker(MarkerOptions().position(seoul).title("Marker in Seoul"))
//
//        val tokyo = LatLng(35.6804, 139.7690)
//        mMap.addMarker(MarkerOptions().position(tokyo).title("Marker in Tokyo"))


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSIONS -> {
                if(grantResults.isNotEmpty()) {
                    for ((i, permission) in permissions.withIndex()) {
                        isPermissionsGranted = grantResults[i] == PackageManager.PERMISSION_GRANTED
                    }
                }
            }
            else -> {
                Toast.makeText(this@MainActivity, "Permissions denied.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
