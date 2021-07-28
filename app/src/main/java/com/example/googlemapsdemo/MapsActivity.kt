package com.example.googlemapsdemo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.googlemapsdemo.databinding.ActivityMapsBinding
import com.example.googlemapsdemo.misc.CameraAndViewport
import com.example.googlemapsdemo.misc.TypeAndStyle
import com.google.android.gms.maps.model.*
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

        // draggableをtrueにすることでマーカーを移動させることができる
        val tokyoMarker = mMap.addMarker(MarkerOptions()
            .position(tokyo).title("Marker in Tokyo")
            .title("Marker in Tokyo")

             // 地図の向きを変更してもマーカーはの向きは変わらない
            .flat(true))

            // 濃淡
//            .alpha(0.5f)

            // 傾き
//            .rotation(90f))
//            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)))
//            .icon(BitmapDescriptorFactory.defaultMarker(26f)))

            // このままだとクラッシュしてしまう
//            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_android_black_24dp)))
//            .icon(fromVectorToBitmap(R.drawable.ic_android_black_24dp, Color.parseColor(("#000000")))))


        tokyoMarker.tag = "Restaurant"

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
//        mMap.setOnMarkerClickListener(this)
//        mMap.setMinZoomPreference(15f)
//        mMap.setMaxZoomPreference(17f)

        // 4000ミリ秒後にズームする
//        lifecycleScope.launch {
//            delay(4000L)
//            mMap.moveCamera(CameraUpdateFactory.zoomBy(3f))
//        }

//        onMapClicked()
//        onMapLongClicked()

//        mMap.setOnMarkerDragListener(this)

        // 4000ミリ秒後にニューヨークに移動する（ピンは東京のまま）
        lifecycleScope.launch {
            delay(2000L)

            // マーカーを消す
//            tokyoMarker.remove()
//            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.tokyo), 1000, object :
//            GoogleMap.CancelableCallback{
//                override fun onFinish() {
//                    Toast.makeText(this@MapsActivity, "Finished", Toast.LENGTH_SHORT).show()
//                }
//
//                // アニメーションの途中で操作した場合に呼び出される
//                override fun onCancel() {
//                    Toast.makeText(this@MapsActivity, "Cancelled", Toast.LENGTH_SHORT).show()
//                }
//            })

            // ズーム（アニメーション）
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(15f), 1000, null)


            //　カメラが右に移動する（アニメーション）
//            mMap.animateCamera(CameraUpdateFactory.scrollBy(200f, 0f), 2000 , null)



//            mMap.moveCamera(CameraUpdateFactory.newLatLng(newyork))

            // ピンはそのままで、地図が移動する
//            mMap.moveCamera(CameraUpdateFactory.scrollBy(100f, 100f))

            // 南西、北東の緯度経度を指定
            // 画面に指定バウンディングボックス＋パディングの領域がおさまるようにカメラが移動する
//            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds, 100))

            // 最初に表示された都市から途切れずに移動する
//            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds, 100), 2000, null)

            // ズーム
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewport.melbourneBounds.center, 10f))

            // スクロールできるエリアを制限する
//            mMap.setLatLngBoundsForCameraTarget(cameraAndViewport.melbourneBounds)

        }
    }

//    // ドラッグ&ドロップした場合にログを表示する
//    override fun onMarkerDragStart(p0: Marker) {
//        Log.d("Drag", "Start")
//    }
//
//    override fun onMarkerDrag(p0: Marker) {
//        Log.d("Drag", "Drag")
//    }
//
//    override fun onMarkerDragEnd(p0: Marker) {
//        Log.d("Drag", "End")
//    }

    // マーカーをクリックするとタグが表示される
//    override fun onMarkerClick(marker: Marker?): Boolean {
//        if(marker != null) {
//            Log.d("Marker", marker.tag as String)
//        } else {
//            Log.d("Marker", "Empty")
//        }
//
//        // trueにするとRunに表示される
//        // falseにすることでアプリにタグ名が表示される
//        return false
//    }

//    private fun onMapClicked(){
//        mMap.setOnMapClickListener {
//          Toast.makeText(this@MapsActivity, "Single Click", Toast.LENGTH_SHORT).show()
//        }
//    }

//    private fun onMapLongClicked() {
//        mMap.setOnMapLongClickListener {
////            Toast.makeText(this@MapsActivity, "Long Click", Toast.LENGTH_SHORT).show()
//
//            // クリックした場所の緯度と経度が表示される
//            Toast.makeText(this@MapsActivity, "${it.latitude}, ${it.longitude}", Toast.LENGTH_SHORT).show()
//            mMap.addMarker((MarkerOptions().position(it).title("Marker in Tokyo")))
//        }
//    }

    // アイコンをカスタマイズすることができる
    private fun fromVectorToBitmap(id: Int, color: Int) : BitmapDescriptor {
        val vectorDrawable : Drawable? = ResourcesCompat.getDrawable(resources, id, null)
        if(vectorDrawable == null) {
            Log.d("MapsActivity", "Resource bot found.")
            return BitmapDescriptorFactory.defaultMarker()
        }
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        DrawableCompat.setTint(vectorDrawable, color)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}