package com.example.githubuser.data.ui.favorit

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.data.database.FavoritUser
import com.example.githubuser.data.ui.main.DetailActivity
import com.example.githubuser.databinding.ItemRowBinding

class FavoritAdapter(private val dataset: List<FavoritUser>) :
    RecyclerView.Adapter<FavoritAdapter.FavoritViewHolder>() {
    inner class FavoritViewHolder(val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: FavoritViewHolder, position: Int) {
        val user = dataset[position]
        holder.binding.tvItemName.text = user.username
        Glide.with(holder.itemView.context).load(user.avatarUrl).into(holder.binding.imgDetailPhoto)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("key_login", user.username)
            holder.itemView.context.startActivity(intent)
        }

    }
}