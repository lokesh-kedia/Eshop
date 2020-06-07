package com.eshop.ui.roomDB.db_Items


import android.app.Application
import com.eshop.ui.roomDB.EshopDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DbItemsRepository internal constructor(application: Application) {
    private val dbItemsDao: DbItemsDao


    init {
        val db = EshopDatabase.getDatabase(application)
        dbItemsDao = db.getItemsDao()
    }


    internal fun insert(mobileGetInpUnits: DbItems) {
        CoroutineScope(IO).launch {
            dbItemsDao.insert(mobileGetInpUnits)
        }
    }

    fun deleteAll() {
        CoroutineScope(IO).launch {
            dbItemsDao.deleteAll()
        }
    }


    suspend fun getAllItems(): List<DbItems>? = withContext(IO) {
        return@withContext dbItemsDao.getAllItems()
    }

    suspend fun getItemByID(id: Long): DbItems? = withContext(IO) {
        return@withContext dbItemsDao.getItemByID(id)
    }

}
