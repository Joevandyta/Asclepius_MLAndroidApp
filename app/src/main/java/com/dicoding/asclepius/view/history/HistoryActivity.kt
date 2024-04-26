package com.dicoding.asclepius.view.history

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.database.HistoryData
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
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
        viewModel.getAllHistory().observe(this){allData ->
            if (allData!=null){
                val list = arrayListOf<HistoryData>()
                allData.map {
                    val historyData = HistoryData(id = it.id, label = it.label.toString(),result = it.result.toString(), image = it.image.toString())

                    list.add(historyData)
                }
                adapter.setList(list)
            }
        }
    }
}