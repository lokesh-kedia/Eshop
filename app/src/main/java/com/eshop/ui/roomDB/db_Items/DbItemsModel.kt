package com.eshop.ui.roomDB.db_Items

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class DbItemsModel(application: Application) : AndroidViewModel(application) {

    private val dbItemsRepository: DbItemsRepository =
        DbItemsRepository(application)

    suspend fun getAllItems(): List<DbItems>? {
        return dbItemsRepository.getAllItems()
    }
    suspend fun getItemByID(id:Long): DbItems? {
        return dbItemsRepository.getItemByID(id)
    }

    fun insert(mobileGetInpUnits: DbItems) {
        dbItemsRepository.insert(mobileGetInpUnits)
    }

    fun deleteAll() {
        dbItemsRepository.deleteAll()
    }
}
