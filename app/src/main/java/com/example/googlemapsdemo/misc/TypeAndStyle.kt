package com.example.googlemapsdemo.misc

import android.content.Context
import android.util.Log
import android.view.MenuItem
import com.example.googlemapsdemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions

class TypeAndStyle {

    // カスタマイズした地図のデザインを反映する
    // https://mapstyle.withgoogle.com/
    fun setMapStyle(googleMap : GoogleMap, context: Context){
        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    context, R.raw.style
                )
            )
            if (!success) {
                Log.d("Maps" , "Failed to add Style.")
            }

        } catch (e: Exception) {
            Log.d("Maps" , e.toString())
        }
    }

    fun setMapType(item: MenuItem, mMap: GoogleMap) {
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
    }
}