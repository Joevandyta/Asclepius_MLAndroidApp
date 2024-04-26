package com.dicoding.asclepius.view.history

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.api.RetrofitClient
import com.dicoding.asclepius.data.Articles
import com.dicoding.asclepius.data.NewsResponse
import com.dicoding.asclepius.database.HistoryData
import com.dicoding.asclepius.repository.HistoryDataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val mHistoryDataRepository: HistoryDataRepository = HistoryDataRepository(application)

    fun saveHistory(historyData: HistoryData) {
        mHistoryDataRepository.insertHistory(historyData)
    }

    fun getAllHistory(): LiveData<List<HistoryData>> = mHistoryDataRepository.getAllHistory()

}
