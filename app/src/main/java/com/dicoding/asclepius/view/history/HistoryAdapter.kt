package com.dicoding.asclepius.view.history

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.database.HistoryData
import com.dicoding.asclepius.databinding.ItemHistoryRowBinding
import com.dicoding.asclepius.helper.ClassifyDiffCallback

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private val listHistory = ArrayList<HistoryData>()
    private var onItemClickCallBack: OnItemClickCallBack? = null

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    fun setList(listNotes: List<HistoryData>) {
        val diffCallback = ClassifyDiffCallback(this.listHistory, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listHistory.clear()
        this.listHistory.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listHistory[position])

    override fun getItemCount(): Int = listHistory.size

    inner class ViewHolder(private val binding: ItemHistoryRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(historyData: HistoryData) {
            binding.root.setOnClickListener {
                onItemClickCallBack?.onItemClicked(historyData)
            }
            with(binding) {
                imgItemResult.setImageURI(Uri.parse( historyData.image))
                tvItemResult.text = historyData.result
                tvItemLabel.text = historyData.label
            }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: HistoryData)
    }
}


