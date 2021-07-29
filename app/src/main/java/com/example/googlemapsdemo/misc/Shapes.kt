package com.example.googlemapsdemo.misc

import android.graphics.Color
import com.example.googlemapsdemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.delay

class Shapes {

    // Add a marker in Sydney and move the camera
    private val tokyo = LatLng(35.68879981093124, 139.77244454788328)
    private val taiwan = LatLng(23.459621515018753, 121.20255613443851)
    private val hokkaido = LatLng(43.34528002870465, 142.6073483968095)
    private val beijing = LatLng(39.92169741521567, 116.40292456525955)

    private val p0 = LatLng(43.77926036143497, 141.75937963139538)
    private val p1 = LatLng(43.763338671609404, 144.48242365979098)
    private val p2 = LatLng(42.905352036232614, 143.82095547475564)
    private val p3 = LatLng(41.953320562378565, 140.667957126087)

    private val p00 = LatLng(43.34631613118171, 141.9811465005982)
    private val p01 = LatLng(43.34196834508504, 143.15292078920422)
    private val p02 = LatLng(42.774105813702384, 143.0602549653604)
    private val p03 = LatLng(42.923131508650556, 141.94527585911024)


        // 2箇所を結ぶ線を引く
    suspend fun addPolyline(mMap: GoogleMap){

        // 線のパターンを定義している
//        val pattern = listOf(Dot(), Gap(30f), Dash(50f), Gap(30f))
//        val pattern = listOf(Dot(), Gap(30f))
        val polyline = mMap.addPolyline(
            PolylineOptions().apply {
                add(tokyo, taiwan, hokkaido)
                width(120f)
                color(Color.BLUE)

                // 真っ直ぐの線ではなく、カーブの線を引く
                geodesic(true)
                clickable(true)
//                pattern(pattern)
                jointType(JointType.ROUND)
                startCap(CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker), 100f))
//                startCap(RoundCap())
//                startCap(CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.common_google_signin_btn_icon_dark_focused)))
                endCap(RoundCap())

            }
        )

        delay(10000)

        val newList = listOf(
            tokyo, beijing, hokkaido
        )

//        polyline.points = newList
            polyline.points = listOf(tokyo, beijing, hokkaido)
    }

    fun addPolygon(mMap: GoogleMap){
        val polygon = mMap.addPolygon(
            PolygonOptions().apply {
                add(p0, p1, p2, p3)
                fillColor(R.color.black)
                strokeColor(R.color.black)
                zIndex(1f)


                // 穴を開けることができる
//                addHole(listOf(p00, p01, p02, p03))
            }
        )

        val polygon2 = mMap.addPolygon(
            PolygonOptions().apply {
                add(p00, p01, p02, p03)
                fillColor(R.color.black)
                strokeColor(R.color.black)


            }
        )
    }

    suspend fun addCircle(mMap: GoogleMap){
        val circle = mMap.addCircle(
            CircleOptions().apply {
                center(hokkaido)
                radius(50000.0)
                fillColor(R.color.purple_200)
            }
        )

        delay(4000L)

        // 色が変わる
        circle.fillColor = R.color.black
    }
}