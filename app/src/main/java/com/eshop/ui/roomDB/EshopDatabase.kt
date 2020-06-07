package com.eshop.ui.roomDB

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eshop.ui.roomDB.db_Cart.DbCart
import com.eshop.ui.roomDB.db_Cart.DbCartDao
import com.eshop.ui.roomDB.db_Items.DbItems
import com.eshop.ui.roomDB.db_Items.DbItemsDao

import com.eshop.ui.roomDB.db_shops.DbShops
import com.eshop.ui.roomDB.db_shops.DbShopsDao

@Database(
    entities = [DbShops::class,DbItems::class,DbCart::class],
    version = 3,
    exportSchema = true
)
abstract class EshopDatabase : RoomDatabase() {


    abstract fun getShopsDao(): DbShopsDao
    abstract fun getItemsDao(): DbItemsDao
    abstract fun getCartDao(): DbCartDao


    private class PopulateDbAsync internal constructor(db: EshopDatabase) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void): Void? {
            return null
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: EshopDatabase? = null
        fun getDatabase(context: Context): EshopDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EshopDatabase::class.java,
                        "EshopDatabase"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}