package com.dicoding.asclepius.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(historyData: HistoryData)

    @Delete
    fun delete(historyData: HistoryData)

    @Query("SELECT * from historydata")
    fun getAllHistory(): LiveData<List<HistoryData>>

    @Query("SELECT * FROM historydata WHERE id = :id")
    fun getHistory(id: Int): LiveData<HistoryData>
}