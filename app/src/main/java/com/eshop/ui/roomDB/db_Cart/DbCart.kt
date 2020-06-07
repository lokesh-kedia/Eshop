package com.eshop.ui.roomDB.db_Cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DbCart")
class DbCart(
    @field:PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val itemQuantity: Int
)
