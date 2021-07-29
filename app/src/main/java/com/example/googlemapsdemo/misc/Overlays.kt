package com.example.googlemapsdemo.misc

import com.example.googlemapsdemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.google.android.gms.maps.model.LatLng

class Overlays {

    private val hokkaido = LatLng(43.34528002870465, 142.6073483968095)

    fun addGroundOverlay(map : GoogleMap){
        val groundOverlay = map.addGroundOverlay(
            GroundOverlayOptions().apply {
                position(hokkaido, 1000f, 1000f)
                image(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker))
            }
        )

    }
}