package com.dicoding.asclepius.view.result

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.database.HistoryData
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.view.history.HistoryViewModel

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var viewModel: HistoryViewModel
    private var historyData: HistoryData? = null
    private var onHistory = false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        val result = intent.getStringExtra(EXTRA_RESULT)
        viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]

        binding.apply {
            resultText.text = result
            resultImage.setImageURI(imageUri)

        }
    }

//        isSaved()
//        viewModel.saveHistory(
//            HistoryData(
//                result = intent.getStringExtra(EXTRA_RESULT),
//                image = intent.getStringExtra(EXTRA_IMAGE_URI),
//                label = intent.getStringExtra(EXTRA_LABEL)
//            )
//        )
//        historyData?.let {
//            if (!onHistory) {
//                onHistory = true
//                viewModel.saveHistory(it)
//            }
//        }



//    private fun isSaved() {
//        viewModel.savedHistory.observe(this) {
//            if (it == true) {
//                onHistory = true
//            } else {
//                onHistory = false
//
//            }
//        }
//    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_LABEL = "extra_label"
    }

}