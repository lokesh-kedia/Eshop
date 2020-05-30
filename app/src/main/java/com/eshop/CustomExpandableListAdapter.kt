package com.eshop

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.GridView
import com.eshop.ui.home.CategoryGridAdapter
import com.eshop.ui.home.CategoryModel


class CustomExpandableListAdapter(
    private val context: Context,
    private val expandableListTitle: List<String>,
    private val expandableListDetail: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {
    override fun getChild(listPosition: Int, expandedListPosition: Int): List<String>? {
        return expandableListDetail[expandableListTitle[listPosition]]
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
        val expandedListText =
            getChild(listPosition, expandedListPosition)
        if (convertView == null) {
            val layoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_expanded_category_child, null)
        }
        Log.d("getChildView", "getChildView: "+"Called")
        val gridView = convertView?.findViewById(R.id.child_grid) as GridView
        val list = ArrayList<CategoryModel>()
        if (expandedListText != null) {
            for (text in expandedListText) {
                list.add(CategoryModel(R.drawable.ic_fruits, text))
            }
        }
        val gridAdapter = CategoryGridAdapter(context, list)
        gridView.adapter = gridAdapter
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return 1
    }

    override fun getGroup(listPosition: Int): Any {
        return expandableListTitle[listPosition]
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
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_expanded_category_parent, null)
        }
        //val listTitleTextView = convertView?.findViewById(R.id.account_tag) as TextView
        //listTitleTextView.text = listTitle
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