package com.example.githubuser.data.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.data.response.ListFollowResponseItem
import com.example.githubuser.databinding.ItemRowBinding

class FollowAdapter :
    ListAdapter<ListFollowResponseItem, FollowAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    class MyViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: ListFollowResponseItem) {
            binding.tvItemName.text = review.login
            Glide.with(itemView.context)
                .load(review.avatarUrl)
                .into(binding.imgDetailPhoto)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListFollowResponseItem>() {
            override fun areItemsTheSame(
                oldItem: ListFollowResponseItem,
                newItem: ListFollowResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListFollowResponseItem,
                newItem: ListFollowResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}