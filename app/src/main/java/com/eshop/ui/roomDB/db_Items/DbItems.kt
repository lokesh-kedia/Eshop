package com.eshop.ui.roomDB.db_Items

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DbItems")
class DbItems(
    val itemName: String,
    val itemCategory: String,
    val itemPrice: Int,
    val itemQuantity: String,
    val discount: Int,
    val itemImage: ByteArray
) {
    @field:PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
