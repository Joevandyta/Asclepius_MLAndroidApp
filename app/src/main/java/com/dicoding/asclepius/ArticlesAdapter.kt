package com.dicoding.asclepius

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.data.Articles
import com.dicoding.asclepius.database.HistoryData
import com.dicoding.asclepius.databinding.ItemArticleRowBinding
import com.dicoding.asclepius.databinding.ItemHistoryRowBinding
import com.dicoding.asclepius.helper.ClassifyDiffCallback

class ArticlesAdapter(private val article: List<Articles>): RecyclerView.Adapter<ArticlesAdapter.ViewHolder>(){

    private var onItemClickCallback: OnItemClickCallBack? = null

    fun setOnItemClickCallback(onItemClickCallback: ArticlesAdapter.OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallback
    }


    inner class ViewHolder(private val binding: ItemArticleRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(articles: Articles) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(articles)
            }
            binding.apply {
                Glide.with(binding.root)
                    .load(articles.urlToImage)
                    .error(R.drawable.ic_place_holder)
                    .into(imgItemArticle)

                tvItemTitle.text = articles.title
                tvItemDescription.text = articles.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesAdapter.ViewHolder {
        val binding = ItemArticleRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticlesAdapter.ViewHolder, position: Int) = holder.bind(article[position])

    override fun getItemCount(): Int = article.size

    interface OnItemClickCallBack {
        fun onItemClicked(data: Articles)
    }
}