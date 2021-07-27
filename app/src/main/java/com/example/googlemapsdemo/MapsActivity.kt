package com.example.googlemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.googlemapsdemo.databinding.ActivityMapsBinding
import com.example.googlemapsdemo.misc.CameraAndViewport
import com.example.googlemapsdemo.misc.TypeAndStyle
import com.google.android.gms.maps.model.MapStyleOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val typeAndStyle = TypeAndStyle()
//    private val cameraAndViewport = CameraAndViewport()

    // lazyを使ったインスタンス作成
    private val cameraAndViewport by lazy {CameraAndViewport()}

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
        typeAndStyle.setMapType(item, mMap)
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val tokyo = LatLng(35.68879981093124, 139.77244454788328)
        val newyork = LatLng(40.75620413149381, -73.98724093807755)
        mMap.addMarker(MarkerOptions().position(tokyo).title("Marker in tokyo"))
//        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.tokyo))

        // newLatLngZoomにすることで初期表示する際のズームレベルを指定できる
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tokyo, 10f))
//        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.tokyo))
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

        typeAndStyle.setMapStyle(mMap, this)
//        mMap.setMinZoomPreference(15f)
//        mMap.setMaxZoomPreference(17f)

        // 4000ミリ秒後にズームする
//        lifecycleScope.launch {
//            delay(4000L)
//            mMap.moveCamera(CameraUpdateFactory.zoomBy(3f))
//        }

        // 4000ミリ秒後にニューヨークに移動する（ピンは東京のまま）
        lifecycleScope.launch {
            delay(4000L)
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(newyork))

            // ピンはそのままで、地図が移動する
//            mMap.moveCamera(CameraUpdateFactory.scrollBy(100f, 100f))

            // 南西、北東の緯度経度を指定
            // 画面に指定バウンディングボックス＋パディングの領域がおさまるようにカメラザ行を移動する
//            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds, 100))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewport.melbourneBounds.center, 10f))
        }
    }


}