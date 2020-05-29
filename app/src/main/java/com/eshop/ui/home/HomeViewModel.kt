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
            CategoryModel(R.drawable.ic_address, "Grocery & Staples"),
            CategoryModel(R.drawable.ic_address, "Home & Kitchen"),
            CategoryModel(R.drawable.ic_address, "HouseHold Items"),
            CategoryModel(R.drawable.ic_address, "Personal Care"),
            CategoryModel(R.drawable.ic_address, "Hygiene First"),
            CategoryModel(R.drawable.ic_address, "Beverages"),
            CategoryModel(R.drawable.ic_address, "Breakfast & Dairy"),
            CategoryModel(R.drawable.ic_address, "Baby & Pet Care"),
            CategoryModel(R.drawable.ic_address, "Packaged Food"),
            CategoryModel(R.drawable.ic_address, "Lowest Price"),
            CategoryModel(R.drawable.ic_address, "Best Value"),
            CategoryModel(R.drawable.ic_address, "Fruits & Vegetable")
        )
    }
    val listCategory: LiveData<ArrayList<CategoryModel>> = _listCategory
}