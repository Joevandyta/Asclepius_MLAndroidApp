package com.dicoding.asclepius.view.history

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.asclepius.database.HistoryData
import com.dicoding.asclepius.repository.HistoryDataRepository

class HistoryViewModel(application: Application): AndroidViewModel(application) {
    val history = MutableLiveData<HistoryData>()
    private val mHistoryDataRepository: HistoryDataRepository = HistoryDataRepository(application)
    val savedHistory = MutableLiveData<Boolean>()



    fun getHistory(): LiveData<HistoryData> {
        return history
    }

    fun saveHistory(historyData: HistoryData) {
        mHistoryDataRepository.insertHistory(historyData)
    }

    fun deleteHistory(historyData: HistoryData) {
        mHistoryDataRepository.deleteHistory(historyData)
    }
    fun getAllHistory(): LiveData<List<HistoryData>> = mHistoryDataRepository.getAllHistory()

    fun isSavedHistory(id: Int): LiveData<Boolean> {
        mHistoryDataRepository.getHistory(id).observeForever {
            if (it != null) savedHistory.postValue(true)
            else savedHistory.postValue(false)
        }
        return savedHistory
    }
}