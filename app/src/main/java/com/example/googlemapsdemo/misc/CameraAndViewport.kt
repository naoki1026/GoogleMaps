package com.example.googlemapsdemo.misc

import android.graphics.Camera
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class CameraAndViewport {
    val tokyo : CameraPosition = CameraPosition.builder()
        .target(LatLng(35.68879981093124, 139.77244454788328))
        .zoom(17f)

        // 向き
        .bearing(0f)

        // 傾斜角
        .tilt(45f)
        .build()

    val melbourneBounds = LatLngBounds(
        LatLng(-38.5403356980682, 144.2607619154762), //SW
        LatLng(-37.27447511931346, 145.7620306653769), //NE

    )

}