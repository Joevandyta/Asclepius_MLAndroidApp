package com.dicoding.asclepius.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [HistoryData::class], version = 1)
abstract class HistoryRoomDatabase: RoomDatabase() {
    abstract fun  historyDao(): HistoryDao

    companion object{
        @Volatile
        private var INSTANCE: HistoryRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): HistoryRoomDatabase{
            if (INSTANCE == null){
                synchronized(HistoryRoomDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        HistoryRoomDatabase::class.java,"history_data_database")
                        .build()

                }

            }
            return INSTANCE as HistoryRoomDatabase
        }
    }
}
