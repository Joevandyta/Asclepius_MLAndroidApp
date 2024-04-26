package com.dicoding.asclepius.view.result

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.data.Articles
import com.dicoding.asclepius.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var viewModel: ResultViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        val result = intent.getStringExtra(EXTRA_RESULT)
        viewModel = ViewModelProvider(this)[ResultViewModel::class.java]
        binding.apply {
            rvNews.layoutManager = LinearLayoutManager(this@ResultActivity)
            rvNews.setHasFixedSize(true)
        }
        bindingView(imageUri, result.toString())
    }

    private fun bindingView(imageUri: Uri, result: String){
        viewModel.setSearchArticle()
        viewModel.listArticles.observe(this) {
            val adapter = ArticlesAdapter(it)
            binding.rvNews.adapter = adapter
            adapter.setOnItemClickCallback(object : ArticlesAdapter.OnItemClickCallBack {
                override fun onItemClicked(data: Articles) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
                    startActivity(intent)
                }
            })
        }
        binding.apply {
            resultText.text = result
            resultImage.setImageURI(imageUri)
        }
    }
    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_LABEL = "extra_label"
    }
}