package com.eshop.ui.roomDB.db_Cart


import android.app.Application
import com.eshop.ui.roomDB.EshopDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DbCartRepository internal constructor(application: Application) {
    private val dbCartDao: DbCartDao


    init {
        val db = EshopDatabase.getDatabase(application)
        dbCartDao = db.getCartDao()
    }


    internal fun insert(mobileGetInpUnits: DbCart) {
        CoroutineScope(IO).launch {
            dbCartDao.insert(mobileGetInpUnits)
        }
    }

    fun deleteAll() {
        CoroutineScope(IO).launch {
            dbCartDao.deleteAll()
        }
    }


    suspend fun getAllCart(): List<DbCart>? = withContext(IO) {
        return@withContext dbCartDao.getAllCart()
    }

}
