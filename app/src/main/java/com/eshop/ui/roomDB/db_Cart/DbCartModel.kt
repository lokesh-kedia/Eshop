package com.eshop.ui.roomDB.db_Cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class DbCartModel(application: Application) : AndroidViewModel(application) {

    private val dbCartRepository: DbCartRepository =
        DbCartRepository(application)

    suspend fun getAllCart(): List<DbCart>? {
        return dbCartRepository.getAllCart()
    }

    fun insert(mobileGetInpUnits: DbCart) {
        dbCartRepository.insert(mobileGetInpUnits)
    }

    fun deleteAll() {
        dbCartRepository.deleteAll()
    }
}
