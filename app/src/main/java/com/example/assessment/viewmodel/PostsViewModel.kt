package com.example.assessment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.assessment.repo.ListPostsRepo
import com.example.assessment.model.PostResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(repository: ListPostsRepo) : ViewModel() {

    val posts: Flow<PagingData<PostResponse>> = repository.getData().cachedIn(viewModelScope)

}