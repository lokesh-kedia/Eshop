package com.eshop.ui.allCategories

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.eshop.R
import com.eshop.ui.home.CategoryGridAdapter
import com.eshop.ui.home.CategoryModel


class CustomExpandableListAdapter(
    private val context: Context,
    private val expandableListTitle: ArrayList<AllCategoryModel>
) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, expandedListPosition: Int): ArrayList<HeaderDetails> {
        return expandableListTitle[listPosition].childDetails
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean, convertView1: View?, parent: ViewGroup?
    ): View? {
        var convertView: View? = convertView1
        val childList =
            getChild(listPosition, expandedListPosition)
        if (convertView == null) {
            val layoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_expanded_category_child, null)
        }
        Log.d("getChildView", "getChildView: " + "Called")
        val gridView = convertView?.findViewById(R.id.child_grid) as GridView
        val list = ArrayList<CategoryModel>()
        for (child in childList) {
            list.add(CategoryModel(child.img, child.name))
        }
        val gridAdapter = CategoryGridAdapter(context, list)
        gridView.adapter = gridAdapter
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return 1
    }

    override fun getGroup(listPosition: Int): HeaderDetails {
        return expandableListTitle[listPosition].headerDetails
    }

    override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int, isExpanded: Boolean,
        convertView1: View?, parent: ViewGroup?
    ): View? {
        Log.d("AccountTest", "getGroupView")
        var convertView: View? = convertView1
        val headerDetails = getGroup(listPosition)
        if (convertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_expanded_category_parent, null)
        }
        val listTitleTextView = convertView?.findViewById(R.id.text_category_name) as TextView
        val textDiscount = convertView.findViewById(R.id.text_discount) as TextView
        val imgCategory = convertView.findViewById(R.id.img_category) as ImageView
        listTitleTextView.text = headerDetails.name
        textDiscount.text = "UP TO ${headerDetails.discount}% OFF"
        imgCategory.setImageResource(headerDetails.img)
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(
        listPosition: Int,
        expandedListPosition: Int
    ): Boolean {
        return true
    }

}