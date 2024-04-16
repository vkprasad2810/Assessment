package com.example.assessment.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessment.adapter.LoaderAdapter
import com.example.assessment.adapter.PostAdapter
import com.example.assessment.databinding.ActivityMainBinding
import com.example.assessment.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.Duration

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var model: PostsViewModel
    private val adapter by lazy { PostAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )


        adapter.addLoadStateListener { loadState ->

            binding.retryButton.isVisible =loadState.source.refresh is LoadState.Error
            binding.loading.isVisible =loadState.source.refresh is LoadState.Loading

        }

        binding.retryButton.setOnClickListener {
            adapter.retry()
        }

        model = ViewModelProvider(this)[PostsViewModel::class.java]

        lifecycleScope.launch {
            model.posts.collectLatest {

                adapter.submitData(it)

            }
        }


    }
}