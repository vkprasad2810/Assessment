package com.example.assessment.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment.model.PostResponse

class PostAdapter : PagingDataAdapter<PostResponse, RecyclerView.ViewHolder>(COMPARATOR) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val postsItem = getItem(position)
        if (postsItem != null) {
            (holder as? PostsViewHolder)?.bind(postsItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostsViewHolder.create(parent)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PostResponse>() {
            override fun areItemsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean =
                oldItem == newItem

        }
    }
}