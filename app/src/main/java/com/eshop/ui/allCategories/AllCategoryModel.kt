package com.eshop.ui.allCategories

data class AllCategoryModel(
    var headerDetails: HeaderDetails,
    var childDetails: ArrayList<HeaderDetails>
)

data class HeaderDetails(
    var img: Int,
    var discount: Int,
    var name: String
)

