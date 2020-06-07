package com.eshop.ui.roomDB.db_Cart

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eshop.ui.roomDB.db_Cart.DbCart


@Dao
interface DbCartDao {
    @Query("SELECT * from DbCart")
    suspend fun getAllCart(): List<DbCart>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mobileGetDarConfigs: DbCart)

    @Query("DELETE FROM DbCart")
    fun deleteAll()

}
