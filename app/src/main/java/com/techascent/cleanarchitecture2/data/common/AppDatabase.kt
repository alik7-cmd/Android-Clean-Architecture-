package com.techascent.cleanarchitecture2.data.common

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.techascent.cleanarchitecture2.BuildConfig
import com.techascent.cleanarchitecture2.data.user.dao.UserDao
import com.techascent.cleanarchitecture2.domain.user.entity.LocalUserEntity

@Database(entities = arrayOf(LocalUserEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun userDao() : UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    ).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, BuildConfig.APPLICATION_ID
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }
            }).allowMainThreadQueries()
                .build()
    }

}