package com.eshop.ui.item

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.eshop.R
import com.eshop.ui.roomDB.db_Items.DbItems
import kotlinx.android.synthetic.main.item_item.view.*


class ItemRecyclerAdapter(
    private val itemList: List<DbItems>,
    private val context: Context,
    private var interfaceItemRecycler: InterfaceItemRecycler,
    private var hashMap: HashMap<Long, Int>
) : RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemRecyclerAdapter.ViewHolder, position: Int) {

        val item = itemList[position]
        holder.itemPrice.text = "₹${item.itemPrice}"
        holder.itemName.text = item.itemName
        holder.textDiscount.text = "${item.discount}% OFF"
        holder.itemQuantity.text = item.itemQuantity
        val bitmap = BitmapFactory.decodeByteArray(item.itemImage, 0, item.itemImage.size)
        holder.itemImg.setImageBitmap(bitmap)
        if (hashMap.containsKey(item.id)) {
            holder.itemAdd.visibility = View.GONE
            holder.group.visibility = View.VISIBLE
            holder.itemInCart.text = "${hashMap[item.id]}"
            holder.itemPlus.setOnClickListener {
                holder.itemInCart.text = (hashMap[item.id]!! + 1).toString()
                interfaceItemRecycler.onPlusClicked(item, hashMap[item.id]!!)
            }
            holder.itemMinus.setOnClickListener {
                holder.itemInCart.text = (hashMap[item.id]!! - 1).toString()
                interfaceItemRecycler.onMinusClicked(
                    item,
                    hashMap[item.id]!!
                )
            }
        } else {
            holder.itemAdd.setOnClickListener {
                holder.itemAdd.visibility = View.GONE
                holder.group.visibility = View.VISIBLE
                holder.itemInCart.text = "1"
                holder.itemPlus.setOnClickListener {
                    interfaceItemRecycler.onPlusClicked(
                        item,
                        holder.itemInCart.text.toString().toInt()
                    )
                }
                holder.itemMinus.setOnClickListener {
                    interfaceItemRecycler.onMinusClicked(
                        item,
                        holder.itemInCart.text.toString().toInt()
                    )
                }
            }
        }
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val itemImg: ImageView = mView.item_img
        val itemPrice: TextView = mView.item_price
        val itemName: TextView = mView.item_name
        val textDiscount: TextView = mView.text_discount
        val itemQuantity: TextView = mView.item_quantity
        val itemAdd: Button = mView.item_add
        val itemPlus: TextView = mView.item_plus
        val itemMinus: TextView = mView.item_minus
        val itemInCart: TextView = mView.item_in_cart
        val group: Group = mView.group

    }

    interface InterfaceItemRecycler {
        fun onPlusClicked(dbItems: DbItems, itemQuantity: Int)
        fun onMinusClicked(dbItems: DbItems, itemQuantity: Int)
    }
}