package com.dicoding.asclepius.view.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dicoding.asclepius.database.HistoryData
import com.dicoding.asclepius.repository.HistoryDataRepository

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val mHistoryDataRepository: HistoryDataRepository = HistoryDataRepository(application)

    fun saveHistory(historyData: HistoryData) {
        mHistoryDataRepository.insertHistory(historyData)
    }

    fun getAllHistory(): LiveData<List<HistoryData>> = mHistoryDataRepository.getAllHistory()
}
