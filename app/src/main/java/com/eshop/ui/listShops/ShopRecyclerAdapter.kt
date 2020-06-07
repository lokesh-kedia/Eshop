package com.eshop.ui.listShops

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.eshop.R
import com.eshop.ui.item.ItemListActivity
import com.eshop.ui.roomDB.db_shops.DbShops
import kotlinx.android.synthetic.main.item_shop.view.*

class ShopRecyclerAdapter(
    private val itemList: List<DbShops>,
    private val context: Context
) : RecyclerView.Adapter<ShopRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShopRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shop, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ShopRecyclerAdapter.ViewHolder, position: Int) {

        val item = itemList[position]
        holder.shopName.text = item.shopName
        holder.shopItem.setOnClickListener {
            /*val gmmIntentUri: Uri =
                Uri.parse("google.navigation:q=${item.latitude},${item.longitude}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(mapIntent)
            }*/
            val intent = Intent(context, ItemListActivity::class.java)
            intent.putExtra("classType","ItemList")
            context.startActivity(intent)
        }
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val itemImg: ImageView = mView.shop_img
        val shopItem: ConstraintLayout = mView.shop_item
        val shopName: TextView = mView.shop_name
        val shopCategory: TextView = mView.shop_category
    }

}