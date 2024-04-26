package com.dicoding.asclepius.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.asclepius.database.HistoryData
import com.dicoding.asclepius.database.HistoryDao
import com.dicoding.asclepius.database.HistoryRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HistoryDataRepository(application: Application) {

    private val mHistory: HistoryDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = HistoryRoomDatabase.getDatabase(application)
        mHistory = db.historyDao()
    }

    fun getAllHistory(): LiveData<List<HistoryData>> = mHistory.getAllHistory()

    fun insertHistory(historyData: HistoryData) {
        executorService.execute { mHistory.insert(historyData) }
    }

    fun deleteHistory(historyData: HistoryData) {
        executorService.execute { mHistory.delete(historyData) }
    }

    fun getHistory(id: Int): LiveData<HistoryData> {
        return mHistory.getHistory(id)
    }
}