package com.eshop.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eshop.R

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _listCategory = MutableLiveData<ArrayList<CategoryModel>>().apply {
        value = arrayListOf(
            CategoryModel(R.drawable.ic_grocery, "Grocery & Staples"),
            CategoryModel(R.drawable.ic_kitchen, "Home & Kitchen"),
            CategoryModel(R.drawable.ic_household, "HouseHold Items"),
            CategoryModel(R.drawable.ic_personal, "Personal Care"),
            CategoryModel(R.drawable.ic_hygiene, "Hygiene First"),
            CategoryModel(R.drawable.ic_beverages, "Beverages"),
            CategoryModel(R.drawable.ic_breakfast, "Breakfast & Dairy"),
            CategoryModel(R.drawable.ic_child_care, "Baby & Pet Care"),
            CategoryModel(R.drawable.ic_packaged_food, "Packaged Food"),
            CategoryModel(R.drawable.ic_lowest_price, "Lowest Price"),
            CategoryModel(R.drawable.ic_best_value, "Best Value"),
            CategoryModel(R.drawable.ic_fruits, "Fruits & Vegetable")
        )
    }
    val listCategory: LiveData<ArrayList<CategoryModel>> = _listCategory
}