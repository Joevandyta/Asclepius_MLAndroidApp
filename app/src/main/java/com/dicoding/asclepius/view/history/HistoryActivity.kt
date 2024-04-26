package com.dicoding.asclepius.view.history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.HistoryAdapter
import com.dicoding.asclepius.R
import com.dicoding.asclepius.database.HistoryData
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.view.result.ResultActivity

class HistoryActivity : AppCompatActivity() {
    private lateinit var viewModel: HistoryViewModel
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var adapter: HistoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = HistoryAdapter()
        adapter.setOnItemClickCallBack(object : HistoryAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: HistoryData) {
                val intent = Intent(this@HistoryActivity, ResultActivity::class.java)
                intent.putExtra(ResultActivity.EXTRA_IMAGE_URI, data.image.toString())
                intent.putExtra(ResultActivity.EXTRA_RESULT, data.result.toString())
                intent.putExtra(ResultActivity.EXTRA_LABEL, data.label.toString())
                startActivity(intent)
            }
        })

        viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]

        binding.apply {
            rvHistory.layoutManager = LinearLayoutManager(this@HistoryActivity)
            rvHistory.setHasFixedSize(true)
            rvHistory.adapter = adapter
        }
        viewModel.getAllHistory().observe(this){
            if (it!=null){
                val list = arrayListOf<HistoryData>()
                it.map {
                    val historyData = HistoryData(id = it.id, label = it.label.toString(),result = it.result.toString(), image = it.image.toString())

                    list.add(historyData)
                }
//                onProgressBar(false)
                adapter.setList(list)
            }else showToast("Rusak bang")
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}