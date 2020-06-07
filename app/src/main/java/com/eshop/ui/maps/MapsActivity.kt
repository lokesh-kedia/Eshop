package com.eshop.ui.maps

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.eshop.R
import com.eshop.ui.addItem.AddItemActivity
import com.eshop.ui.addShop.AddShopActivity
import com.eshop.ui.listShops.ListShopsActivity
import com.eshop.ui.roomDB.db_shops.DbShopsModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val MY_PERMISSIONS_REQUEST_LOCATION = 8989
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        AddItemActivity.setUpToolbar(this, toolbar)
        Log.d("IntentExtra", "onCreate: ${intent.extras?.getString("ClassType")}")
        when (intent.extras?.getString("ClassType")) {
            "AddShop" -> {
                add_shop_ins.isVisible = true
            }
            "NearBy" -> {
                list_shop.isVisible = true
                list_shop.setOnClickListener {
                    startActivity(Intent(this, ListShopsActivity::class.java))
                }
            }
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private suspend fun getAllShopsFromDB() {
        val dbShopModel = ViewModelProvider(this).get(DbShopsModel::class.java)

        dbShopModel.getAllShops()?.let { listShops ->
            for (shop in listShops) {
                val height = 60
                val width = 60
                val bitmapDraw = getDrawable(R.drawable.sitelocation) as BitmapDrawable
                val b = bitmapDraw.bitmap
                val smallMarker = Bitmap.createScaledBitmap(b, width, height, false)
                withContext(Main) {
                    val marker = mMap.addMarker(
                        MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                            .position(LatLng(shop.latitude, shop.longitude))
                    )
                    val info = InfoWindowData(shop.id, shop.shopName, shop.shopAddress)
                    marker.tag = info
                }
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        when (intent.extras?.getString("ClassType")) {
            "NearBy" -> {
                add_shop_ins.isVisible = false
                CoroutineScope(IO).launch {
                    getAllShopsFromDB()
                }
            }
            "AddShop" -> {
                mMap.setOnMapLongClickListener { latLng ->
                    val height = 60
                    val width = 60
                    val bitmapDraw = getDrawable(R.drawable.sitelocation) as BitmapDrawable
                    val b = bitmapDraw.bitmap
                    val smallMarker = Bitmap.createScaledBitmap(b, width, height, false)
                    mMap.clear()
                    val marker = mMap.addMarker(
                        MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                            .position(latLng)
                    )
                    add_shop_fab.isVisible = marker.isVisible
                    add_shop_fab.setOnClickListener {
                        val intent = Intent(this, AddShopActivity::class.java)
                        intent.putExtra("latLng", latLng)
                        startActivity(intent)
                    }
                }

            }
        }
        mMap.setOnMarkerClickListener { marker ->

            val customInfoWindow = CustomInfoWindowGoogleMap(this)
            mMap.setInfoWindowAdapter(customInfoWindow)
            marker.showInfoWindow()
            false
        }
        Log.d("MapsTest", "onMapReady: ")
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("MapsTest", "Permission: ")
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), MY_PERMISSIONS_REQUEST_LOCATION
            )

            return
        }
        Log.d("MapsTest", "true: ")
        mMap.isMyLocationEnabled = true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("PermissionTest", "onRequestPermissionsResult $requestCode")
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {

                        return
                    }
                    mMap.isMyLocationEnabled = true
                }
                return
            }

            else -> {
                // Ignore all other requests.
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onListClicked(view: View) {}
}

data class InfoWindowData(
    var id: Long,
    var shopName: String,
    var shopAddress: String

)
