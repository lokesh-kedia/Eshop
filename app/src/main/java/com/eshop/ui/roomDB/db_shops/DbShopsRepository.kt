package com.eshop.ui.roomDB.db_shops


import android.app.Application
import androidx.lifecycle.LiveData
import com.eshop.ui.roomDB.EshopDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DbShopsRepository internal constructor(application: Application) {
    private val dbShopsDao: DbShopsDao


    init {
        val db = EshopDatabase.getDatabase(application)
        dbShopsDao = db.getShopsDao()
    }


    internal fun insert(mobileGetInpUnits: DbShops) {
        CoroutineScope(IO).launch {
            dbShopsDao.insert(mobileGetInpUnits)
        }
    }

    fun deleteAll() {
        CoroutineScope(IO).launch {
            dbShopsDao.deleteAll()
        }
    }


    suspend fun getAllShops(): List<DbShops>? = withContext(IO) {
        return@withContext dbShopsDao.getAllShops()
    }

    fun getLiveShops(): LiveData<List<DbShops>> {
        return dbShopsDao.getLiveShops()
    }

}
