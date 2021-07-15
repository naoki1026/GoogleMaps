package com.example.googlemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.googlemapsdemo.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.MapStyleOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    // 地図の種類を選択することができる
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.normal_map -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            R.id.hybrid_map -> {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
            R.id.satellite_map -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }

            // terrain 地形
            R.id.terrain_map -> {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
            R.id.none_map -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NONE
            }
        }
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val tokyo = LatLng(35.68879981093124, 139.77244454788328)
        mMap.addMarker(MarkerOptions().position(tokyo).title("Marker in tokyo"))

        // newLatLngZoomにすることで初期表示する際のズームレベルを指定できる
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tokyo, 10f))
        mMap.uiSettings.apply {

            // ズームボタンが表示される
            isZoomControlsEnabled = true

            // falseにするとズームジェスチャーが使えなくなる
//            isZoomGesturesEnabled = false

            // スクロールできなくする
//            isScrollGesturesEnabled = false

            // location layerをenabledにしないと反映されない
//            isMyLocationButtonEnabled = true

            // 地図を回転させる場合のみ表示される
//            isCompassEnabled = true
        }

        // 地図上に余白を加えることができる、中心がずれるため、ズームした際にずれる
//        mMap.setPadding(0, 0, 300, 0)

        setMapStyle(mMap)
    }

    // カスタマイズした地図のデザインを反映する
    // https://mapstyle.withgoogle.com/
    private fun setMapStyle(googleMap : GoogleMap){
        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.style
                )
            )
            if (!success) {
                Log.d("Maps" , "Failed to add Style.")
            }

        } catch (e: Exception) {
            Log.d("Maps" , e.toString())
        }
    }
}