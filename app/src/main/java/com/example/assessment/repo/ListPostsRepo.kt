package com.example.assessment.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.assessment.model.PostResponse
import com.example.assessment.retrofit.ApiService
import com.example.assessment.utils.PostsPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


 const val PAGE_SIZE = 10

class ListPostsRepo @Inject constructor(private val postsApi: ApiService) {

    fun getData(): Flow<PagingData<PostResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PostsPagingSource(postsApi = postsApi)
            }
        ).flow
    }


}