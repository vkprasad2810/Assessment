package com.example.assessment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assessment.databinding.ActivityPostsDetailsBinding
import com.example.assessment.model.PostResponse
import com.google.gson.Gson

class PostsDetails : AppCompatActivity() {
    private lateinit var binding: ActivityPostsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = Gson().fromJson(intent.getStringExtra("data"), PostResponse::class.java)

        binding.idValue.text = "${data?.id}"
        binding.userIdValue.text = "${data?.userId}"
        binding.titleValue.text = data?.title
        binding.descriptionValue.text = data?.body

    }
}