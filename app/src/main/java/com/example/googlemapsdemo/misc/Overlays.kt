package com.example.googlemapsdemo.misc

import com.example.googlemapsdemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

class Overlays {

    private val hokkaido = LatLng(43.34528002870465, 142.6073483968095)
    private val hokkaido2 = LatLngBounds(
        LatLng(42.90426244646101, 141.6405515311285),
        LatLng(44.03639340182751, 143.51921362239494)
    )


    fun addGroundOverlay(map : GoogleMap) : GroundOverlay{
       return map.addGroundOverlay(
            GroundOverlayOptions().apply {
                positionFromBounds(hokkaido2)
//                position(hokkaido, 1000f)
                image(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker))
            }
        )
    }
}