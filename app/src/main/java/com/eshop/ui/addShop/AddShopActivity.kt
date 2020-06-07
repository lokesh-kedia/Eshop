package com.eshop.ui.addShop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.eshop.R
import com.eshop.ui.addItem.AddItemActivity
import com.eshop.ui.roomDB.db_shops.DbShops
import com.eshop.ui.roomDB.db_shops.DbShopsModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_add_shop.*

class AddShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.InternalTheme)
        setContentView(R.layout.activity_add_shop)
        AddItemActivity.setUpToolbar(this, toolbar)
    }

    fun onProceedClicked(view: View) {
        if (checkValidation()) {
            saveToDB()
            startActivity(Intent(this, AddItemActivity::class.java))
        } else {
            Toast.makeText(this, "All the fields are required", Toast.LENGTH_LONG).show()
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
    private fun saveToDB() {
        Log.d("AddShopTest", "onCreate: ${intent.extras?.get("latLng")}")
        val latLng = intent.extras?.get("latLng") as LatLng
        val dbShopModel = ViewModelProvider(this).get(DbShopsModel::class.java)

        val dbShops = DbShops(
            shop_name_text.text.toString(),
            owner_name_text.text.toString(),
            owner_phone_text.text.toString(),
            shop_address_text.text.toString(),
            shop_area_text.text.toString(),
            shop_city_text.text.toString(),
            shop_state_text.text.toString(),
            false,
            latLng.latitude,
            latLng.longitude
        )
        dbShopModel.insert(dbShops)
    }

    private fun checkValidation(): Boolean {
        if (shop_name_text.text.isNullOrBlank() || owner_name_text.text.isNullOrBlank() || owner_phone_text.text.isNullOrBlank() || shop_address_text.text.isNullOrBlank() || shop_area_text.text.isNullOrBlank() || shop_city_text.text.isNullOrBlank() || shop_state_text.text.isNullOrBlank()) {
            return false
        }
        return true
    }
}