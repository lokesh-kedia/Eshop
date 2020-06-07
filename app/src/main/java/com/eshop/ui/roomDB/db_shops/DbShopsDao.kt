package com.eshop.ui.roomDB.db_shops

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DbShopsDao {
    @Query("SELECT * from DbShops")
    suspend fun getAllShops(): List<DbShops>

    @Query("SELECT * from DbShops")
    fun getLiveShops(): LiveData<List<DbShops>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(mobileGetDarConfigs: DbShops)

    @Query("DELETE FROM DbShops")
    fun deleteAll()

}
