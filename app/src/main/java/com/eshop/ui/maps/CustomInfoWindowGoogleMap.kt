package com.eshop.ui.maps

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.eshop.R
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker


class CustomInfoWindowGoogleMap(ctx: Context) : InfoWindowAdapter {
    private val context: Context = ctx
    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View {
        val view: View = (context as Activity).layoutInflater
            .inflate(R.layout.map_custom_infowindow, null)

        val shopName: TextView = view.findViewById(R.id.shop_name)
        val shopAddress: TextView = view.findViewById(R.id.shop_address)
        val infoWindowData = marker.tag as InfoWindowData
        shopName.text = infoWindowData.shopName
        shopAddress.text = infoWindowData.shopAddress
        return view
    }

}