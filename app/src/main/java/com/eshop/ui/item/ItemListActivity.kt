package com.eshop.ui.item

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eshop.R
import com.eshop.ui.addItem.AddItemActivity
import com.eshop.ui.roomDB.db_Cart.DbCart
import com.eshop.ui.roomDB.db_Cart.DbCartModel
import com.eshop.ui.roomDB.db_Items.DbItems
import com.eshop.ui.roomDB.db_Items.DbItemsModel
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream


class ItemListActivity : AppCompatActivity() {

    private var interfaceItemRecycler: ItemRecyclerAdapter.InterfaceItemRecycler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.InternalTheme)
        setContentView(R.layout.activity_item_list)
        AddItemActivity.setUpToolbar(this, toolbar)

        when (intent.extras?.getString("classType")) {
            "ItemList" -> {
                CoroutineScope(IO).launch {
                    getAllItemsFromDB(true)
                }
            }
            "CartList" -> {
                CoroutineScope(IO).launch {
                    getAllItemsFromCart()
                }
            }
        }


        val dbItemModel = ViewModelProvider(this).get(DbItemsModel::class.java)
        val dbCartModel = ViewModelProvider(this).get(DbCartModel::class.java)

        interfaceItemRecycler = object : ItemRecyclerAdapter.InterfaceItemRecycler {
            override fun onPlusClicked(
                dbItems: DbItems,
                itemQuantity: Int
            ) {
                CoroutineScope(IO).launch {
                    val items = dbItemModel.getItemByID(dbItems.id)
                    items?.let {
                        val dbCart = DbCart(dbItems.id, itemQuantity + 1)
                        dbCartModel.insert(dbCart)
                        //getAllItemsFromDB(false)
                        when (intent.extras?.getString("classType")) {
                            "ItemList" -> {
                                getAllItemsFromDB(false)
                            }
                            "CartList" -> {
                                getAllItemsFromCart()
                            }
                        }
                    }
                }
            }

            override fun onMinusClicked(
                dbItems: DbItems,
                itemQuantity: Int
            ) {
                CoroutineScope(IO).launch {
                    val items = dbItemModel.getItemByID(dbItems.id)
                    items?.let {
                        val dbCart = DbCart(dbItems.id, itemQuantity - 1)
                        dbCartModel.insert(dbCart)
                        when (intent.extras?.getString("classType")) {
                            "ItemList" -> {
                                getAllItemsFromDB(false)
                            }
                            "CartList" -> {
                                getAllItemsFromCart()
                            }
                        }
                    }
                }
            }

        }

    }

    private suspend fun getAllItemsFromCart() {
        val hashMap: HashMap<Long, Int> = HashMap()
        var totalAmount = 0
        val dbCartModel = ViewModelProvider(this).get(DbCartModel::class.java)
        dbCartModel.getAllCart()?.let { itemList ->
            val listItems: ArrayList<DbItems> = ArrayList()
            for (item in itemList) {
                hashMap[item.id] = item.itemQuantity
                val dbItemModel = ViewModelProvider(this).get(DbItemsModel::class.java)
                dbItemModel.getItemByID(item.id)?.let {
                    totalAmount += (it.itemPrice * item.itemQuantity)
                    listItems.add(it)
                }
            }

            withContext(Main) {
                if (listItems.isNotEmpty()) {
                    checkout_btn.isVisible = true
                    text_amount.isVisible = true
                    text_amount.text = "₹ $totalAmount"

                }
                val itemListAdapter = interfaceItemRecycler?.let {
                    ItemRecyclerAdapter(
                        listItems, this@ItemListActivity,
                        it,
                        hashMap
                    )
                }
                list_recycler.layoutManager = LinearLayoutManager(this@ItemListActivity)
                list_recycler.adapter = itemListAdapter
            }
        }
    }

    private suspend fun getAllItemsFromDB(insertExtra: Boolean) {
        val d: Drawable? = getDrawable(R.drawable.ic_grocery)
        val hashMap: HashMap<Long, Int> = HashMap()

        val dbCartModel = ViewModelProvider(this).get(DbCartModel::class.java)
        dbCartModel.getAllCart()?.let { itemList ->
            for (item in itemList) {
                hashMap[item.id] = item.itemQuantity
            }
        }


        val bitmap = (d as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val bitmapData: ByteArray = stream.toByteArray()
        val dbItemModel = ViewModelProvider(this).get(DbItemsModel::class.java)
        //dbItemModel.deleteAll()
        if (insertExtra) {
            var dbItem = DbItems("Chakki Atta", "Grocery", 293, "10kg", 11, bitmapData)
            dbItem.id = 1
            dbItemModel.insert(dbItem)
            dbItem = DbItems("Besan", "Grocery", 77, "1kg", 19, bitmapData)
            dbItem.id = 2
            dbItemModel.insert(dbItem)
            dbItem = DbItems("Rava/Sooji", "Grocery", 27, "500gm", 32, bitmapData)
            dbItem.id = 3
            dbItemModel.insert(dbItem)
        }
        dbItemModel.getAllItems()?.let { listItems ->

            withContext(Main) {
                val itemListAdapter = interfaceItemRecycler?.let {
                    ItemRecyclerAdapter(
                        listItems, this@ItemListActivity,
                        it,
                        hashMap
                    )
                }
                list_recycler.layoutManager = LinearLayoutManager(this@ItemListActivity)
                list_recycler.adapter = itemListAdapter
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