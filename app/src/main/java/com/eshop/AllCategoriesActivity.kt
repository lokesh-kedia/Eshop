package com.eshop

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_all_categories.*

class AllCategoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_categories)


        val expandableListDetail = ExpandableListDataPump.expendableData
        Log.d("AccountTest", expandableListDetail.toString())
        val expandableListTitle = ArrayList<String>(expandableListDetail.keys)
        val expandableListAdapter =
            CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail)
        expanded_categories.setAdapter(expandableListAdapter)

//        expanded_categories.setOnGroupExpandListener {
//            val param = expanded_categories.layoutParams
//            val size = ((expandableListDetail[expandableListTitle[it]]?.size)!! / 3) + 1
//            Log.d("setOnGroup", "size $size")
//            param.height = 3 * expanded_categories.height + 30 + size*100
//            expanded_categories.layoutParams = param
//            expanded_categories.refreshDrawableState()
//            expanded_categories.refreshDrawableState()
//        }
//        expanded_categories.setOnGroupCollapseListener {
//            val param =
//                expanded_categories.layoutParams
//            param.height = LinearLayout.LayoutParams.WRAP_CONTENT
//            expanded_categories.layoutParams = param
//            expanded_categories.refreshDrawableState()
//            expanded_categories.refreshDrawableState()
//        }
    }
}