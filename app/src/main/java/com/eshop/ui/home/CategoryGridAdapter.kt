package com.eshop.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.eshop.R
import com.eshop.ui.item.ItemListActivity
import kotlinx.android.synthetic.main.item_grid_category.view.*

class CategoryGridAdapter(
    private val context: Context,
    private val listCategory: ArrayList<CategoryModel>
) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        view = if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.item_grid_category, parent, false)
        } else {
            convertView
        }
        view.setOnClickListener {
            val intent = Intent(context, ItemListActivity::class.java)
            intent.putExtra("classType","ItemList")
            context.startActivity(intent)
        }
        view.img_category.setImageResource(listCategory[position].img)
        view.category_name.text = listCategory[position].name

        return view
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listCategory.size
    }
}