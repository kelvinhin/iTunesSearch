package com.kelvinhin.itunessearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kelvinhin.itunessearch.data.Results
import com.kelvinhin.itunessearch.databinding.ItemSongBinding

class SongItemAdapter : ListAdapter<Results, SongItemAdapter.SongItemViewHolder>(DiffCallback) {

    class SongItemViewHolder(private var binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (results: Results) {
            binding.result = results
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.trackId == newItem.trackId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemViewHolder {
        return SongItemViewHolder(ItemSongBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SongItemViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }

}