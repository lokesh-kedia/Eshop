package com.eshop.ui.roomDB.db_Items

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DbItemsDao {
    @Query("SELECT * from DbItems")
    suspend fun getAllItems(): List<DbItems>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(mobileGetDarConfigs: DbItems)

    @Query("DELETE FROM DbItems")
    fun deleteAll()

    @Query("SELECT * from DbItems WHERE id =:id")
    suspend fun getItemByID(id: Long): DbItems?

}
