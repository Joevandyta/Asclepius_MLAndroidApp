package com.dicoding.asclepius.view.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.Articles
import com.dicoding.asclepius.databinding.ItemArticleRowBinding

class ArticlesAdapter(private val article: List<Articles>): RecyclerView.Adapter<ArticlesAdapter.ViewHolder>(){
    private var onItemClickCallback: OnItemClickCallBack? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(article[position])

    override fun getItemCount(): Int = article.size

    interface OnItemClickCallBack {
        fun onItemClicked(data: Articles)
    }
}