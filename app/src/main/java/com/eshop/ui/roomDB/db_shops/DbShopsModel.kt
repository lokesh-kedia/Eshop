package com.eshop.ui.roomDB.db_shops

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class DbShopsModel(application: Application) : AndroidViewModel(application) {

    private val dbShopsRepository: DbShopsRepository =
        DbShopsRepository(application)

    suspend fun getAllShops(): List<DbShops>? {
        return dbShopsRepository.getAllShops()
    }

    fun getLiveShops(): LiveData<List<DbShops>> {
        return dbShopsRepository.getLiveShops()
    }

    fun insert(mobileGetInpUnits: DbShops) {
        dbShopsRepository.insert(mobileGetInpUnits)
    }

    fun deleteAll() {
        dbShopsRepository.deleteAll()
    }
}
