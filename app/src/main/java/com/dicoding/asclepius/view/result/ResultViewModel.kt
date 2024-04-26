package com.dicoding.asclepius.view.result

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.api.RetrofitClient
import com.dicoding.asclepius.data.Articles
import com.dicoding.asclepius.data.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ResultViewModel(application: Application) : AndroidViewModel(application) {
    val listArticles = MutableLiveData<List<Articles>>()
    fun setSearchArticle() {
        RetrofitClient.apiInstance
            .getSearchNews("cancer", "health", "en", BuildConfig.API_Key)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {

                    if (response.isSuccessful) {
                        Log.d("API RESPONSE", response.body().toString())
                        listArticles.postValue(response.body()?.articles)
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }
            })
    }
}