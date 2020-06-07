package com.eshop.ui.roomDB.db_shops

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DbShops")
class DbShops(
    val shopName: String,
    val ownerName: String,
    val phone: String,
    val shopAddress: String,
    val shopArea: String,
    val shopCity: String,
    val shopState: String,
    val isDelivery: Boolean,
    val latitude: Double,
    val longitude: Double
) {
    @field:PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
