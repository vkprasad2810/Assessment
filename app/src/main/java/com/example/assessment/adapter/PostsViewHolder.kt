package com.example.assessment.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.assessment.databinding.ItemPostsBinding
import com.example.assessment.model.PostResponse
import com.example.assessment.view.PostsDetails
import com.google.gson.Gson

class PostsViewHolder(private val binding: ItemPostsBinding) : ViewHolder(binding.root) {


    fun bind(item: PostResponse?) {
        binding.idValue.text = "${item?.id}"
        binding.titleValue.text = item?.title

        binding.root.setOnClickListener {
            binding.root.context.startActivity(
                Intent(
                    binding.root.context,
                    PostsDetails::class.java
                )
                    .putExtra("data", Gson().toJson(item))
            )
        }
    }

    companion object {
        fun create(view: ViewGroup): PostsViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val binding = ItemPostsBinding.inflate(inflater, view, false)
            return PostsViewHolder(binding)
        }
    }

}