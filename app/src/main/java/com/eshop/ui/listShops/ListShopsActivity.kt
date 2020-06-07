package com.eshop.ui.listShops

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eshop.R
import com.eshop.ui.addItem.AddItemActivity
import com.eshop.ui.roomDB.db_shops.DbShopsModel
import kotlinx.android.synthetic.main.activity_list_shops.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListShopsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.InternalTheme)
        setContentView(R.layout.activity_list_shops)
        AddItemActivity.setUpToolbar(this, toolbar)
        CoroutineScope(Dispatchers.IO).launch {
            getAllShopsFromDB()
        }

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
                withContext(Dispatchers.Main) {
                    val itemListAdapter = ShopRecyclerAdapter(listShops, this@ListShopsActivity)
                    list_shop_recycler.layoutManager = LinearLayoutManager(this@ListShopsActivity)
                    list_shop_recycler.adapter = itemListAdapter
                }
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
}