package com.eshop.ui.allCategories

import com.eshop.R

object ExpandableListDataPump {
    val expendableData: ArrayList<AllCategoryModel>
        get() {
            val expandableListDetail = ArrayList<AllCategoryModel>()
            var headerDetails = HeaderDetails(
                R.drawable.ic_grocery,
                67,
                "Grocery & Staples"
            )
            var childDetails = ArrayList<HeaderDetails>()
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_grocery,
                    48,
                    "Pulses"
                )
            )
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_grocery,
                    43,
                    "Atta & Other Flours"
                )
            )
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_grocery,
                    67,
                    "Rice & Other Grains"
                )
            )
            var allCategoryModel = AllCategoryModel(
                headerDetails,
                childDetails
            )
            expandableListDetail.add(allCategoryModel)

            headerDetails = HeaderDetails(
                R.drawable.ic_fruits,
                26,
                "Vegetables & Fruits"
            )
            childDetails = ArrayList()
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_fruits,
                    26,
                    "Vegetables"
                )
            )
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_fruits,
                    22,
                    "Fruits"
                )
            )
            allCategoryModel = AllCategoryModel(
                headerDetails,
                childDetails
            )
            expandableListDetail.add(allCategoryModel)

            headerDetails = HeaderDetails(
                R.drawable.ic_personal,
                58,
                "Personal Care"
            )
            childDetails = ArrayList()
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_personal,
                    58,
                    "Personal Care"
                )
            )
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_personal,
                    42,
                    "Safety Must Haves"
                )
            )
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_personal,
                    12,
                    "Bath & Body"
                )
            )
            allCategoryModel = AllCategoryModel(
                headerDetails,
                childDetails
            )
            expandableListDetail.add(allCategoryModel)

            headerDetails = HeaderDetails(
                R.drawable.ic_household,
                65,
                "Household Items"
            )
            childDetails = ArrayList()
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_household,
                    65,
                    "Laundry Detergents"
                )
            )
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_household,
                    38,
                    "Dishwashers"
                )
            )
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_household,
                    13,
                    "Cleaners"
                )
            )
            allCategoryModel = AllCategoryModel(
                headerDetails,
                childDetails
            )
            expandableListDetail.add(allCategoryModel)

            headerDetails = HeaderDetails(
                R.drawable.ic_kitchen,
                83,
                "Home & Kitchen"
            )
            childDetails = ArrayList()
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_kitchen,
                    83,
                    "Cookware"
                )
            )
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_kitchen,
                    48,
                    "Dining"
                )
            )
            childDetails.add(
                HeaderDetails(
                    R.drawable.ic_kitchen,
                    67,
                    "Furnishing"
                )
            )
            allCategoryModel = AllCategoryModel(
                headerDetails,
                childDetails
            )
            expandableListDetail.add(allCategoryModel)

           /* headerDetails = HeaderDetails(R.drawable.ic_kitchen, 67, "Biscuits, Snacks & Chocolates")
            childDetails = ArrayList()
            childDetails.add(HeaderDetails(1, 48, "Personal Care Best offers"))
            childDetails.add(HeaderDetails(1, 48, "Safety Must Haves"))
            childDetails.add(HeaderDetails(1, 48, "Bath & Body"))
            allCategoryModel = AllCategoryModel(headerDetails, childDetails)
            expandableListDetail.add(allCategoryModel)

            headerDetails = HeaderDetails(1, 67, "Beverages")
            childDetails = ArrayList()
            childDetails.add(HeaderDetails(1, 48, "Personal Care Best offers"))
            childDetails.add(HeaderDetails(1, 48, "Safety Must Haves"))
            childDetails.add(HeaderDetails(1, 48, "Bath & Body"))
            allCategoryModel = AllCategoryModel(headerDetails, childDetails)
            expandableListDetail.add(allCategoryModel)

            headerDetails = HeaderDetails(1, 67, "Breakfast & Dairy")
            childDetails = ArrayList()
            childDetails.add(HeaderDetails(1, 48, "Personal Care Best offers"))
            childDetails.add(HeaderDetails(1, 48, "Safety Must Haves"))
            childDetails.add(HeaderDetails(1, 48, "Bath & Body"))
            allCategoryModel = AllCategoryModel(headerDetails, childDetails)
            expandableListDetail.add(allCategoryModel)

            headerDetails = HeaderDetails(1, 67, "Best Value")
            childDetails = ArrayList()
            childDetails.add(HeaderDetails(1, 48, "Personal Care Best offers"))
            childDetails.add(HeaderDetails(1, 48, "Safety Must Haves"))
            childDetails.add(HeaderDetails(1, 48, "Bath & Body"))
            allCategoryModel = AllCategoryModel(headerDetails, childDetails)
            expandableListDetail.add(allCategoryModel)

            headerDetails = HeaderDetails(1, 67, "Noodles, Sauces & Instant Food")
            childDetails = ArrayList()
            childDetails.add(HeaderDetails(1, 48, "Personal Care Best offers"))
            childDetails.add(HeaderDetails(1, 48, "Safety Must Haves"))
            childDetails.add(HeaderDetails(1, 48, "Bath & Body"))
            allCategoryModel = AllCategoryModel(headerDetails, childDetails)
            expandableListDetail.add(allCategoryModel)*/

            return expandableListDetail
        }
}