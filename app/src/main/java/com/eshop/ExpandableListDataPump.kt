package com.eshop

object ExpandableListDataPump {
    val expendableData: HashMap<String, List<String>>
        get() {
            val expandableListDetail =
                HashMap<String, List<String>>()
            val grocery: MutableList<String> = ArrayList()
            grocery.add("Pulses")
            grocery.add("Atta & Other Flours")
            grocery.add("Rice & Other Grains")
            grocery.add("Dry Fruits & Nuts")
            grocery.add("Edible oils")
            expandableListDetail["Grocery"] = grocery

            val vegetables: MutableList<String> = ArrayList()
            vegetables.add("Vegetables")
            vegetables.add("Fruits")
            expandableListDetail["Vegetables"] = vegetables

            var personal: MutableList<String> = ArrayList()
            personal.add("Personal Care Best offers")
            personal.add("Safety Must Haves")
            personal.add("Bath & Body")
            personal.add("Hair Care")
            personal.add("Skin Care")
            personal.add("Oral Care")
            personal.add("Fragrances")
            personal.add("Face Care")
            personal.add("Feminine Hygiene")
            personal.add("Men's Grooming")
            personal.add("Health And Wellness")
            expandableListDetail["Personal"] = personal

            personal = ArrayList()
            personal.add("Personal Care Best offers")
            personal.add("Safety Must Haves")
            personal.add("Bath & Body")
            personal.add("Hair Care")
            personal.add("Skin Care")
            personal.add("Oral Care")
            personal.add("Fragrances")
            personal.add("Face Care")
            personal.add("Feminine Hygiene")
            personal.add("Men's Grooming")
            personal.add("Health And Wellness")
            expandableListDetail["Household"] = personal

            personal = ArrayList()
            personal.add("Personal Care Best offers")
            personal.add("Safety Must Haves")
            personal.add("Bath & Body")
            personal.add("Hair Care")
            personal.add("Skin Care")
            personal.add("Oral Care")
            personal.add("Fragrances")
            personal.add("Face Care")
            personal.add("Feminine Hygiene")
            personal.add("Men's Grooming")
            personal.add("Health And Wellness")
            expandableListDetail["HomeKitchen"] = personal

            personal = ArrayList()
            personal.add("Personal Care Best offers")
            personal.add("Safety Must Haves")
            personal.add("Bath & Body")
            personal.add("Hair Care")
            personal.add("Skin Care")
            personal.add("Oral Care")
            personal.add("Fragrances")
            personal.add("Face Care")
            personal.add("Feminine Hygiene")
            personal.add("Men's Grooming")
            personal.add("Health And Wellness")
            expandableListDetail["Biscuits"] = personal

            personal = ArrayList()
            personal.add("Personal Care Best offers")
            personal.add("Safety Must Haves")
            personal.add("Bath & Body")
            personal.add("Hair Care")
            personal.add("Skin Care")
            personal.add("Oral Care")
            personal.add("Fragrances")
            personal.add("Face Care")
            personal.add("Feminine Hygiene")
            personal.add("Men's Grooming")
            personal.add("Health And Wellness")
            expandableListDetail["Beverages"] = personal

            personal = ArrayList()
            personal.add("Personal Care Best offers")
            personal.add("Safety Must Haves")
            personal.add("Bath & Body")
            personal.add("Hair Care")
            personal.add("Skin Care")
            personal.add("Oral Care")
            personal.add("Fragrances")
            personal.add("Face Care")
            personal.add("Feminine Hygiene")
            personal.add("Men's Grooming")
            personal.add("Health And Wellness")
            expandableListDetail["Breakfast"] = personal

            personal = ArrayList()
            personal.add("Personal Care Best offers")
            personal.add("Safety Must Haves")
            personal.add("Bath & Body")
            personal.add("Hair Care")
            personal.add("Skin Care")
            personal.add("Oral Care")
            personal.add("Fragrances")
            personal.add("Face Care")
            personal.add("Feminine Hygiene")
            personal.add("Men's Grooming")
            personal.add("Health And Wellness")
            expandableListDetail["BestValue"] = personal

            personal = ArrayList()
            personal.add("Personal Care Best offers")
            personal.add("Safety Must Haves")
            personal.add("Bath & Body")
            personal.add("Hair Care")
            personal.add("Skin Care")
            personal.add("Oral Care")
            personal.add("Fragrances")
            personal.add("Face Care")
            personal.add("Feminine Hygiene")
            personal.add("Men's Grooming")
            personal.add("Health And Wellness")
            expandableListDetail["Noodles"] = personal

            return expandableListDetail
        }
}