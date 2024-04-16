package com.example.assessment.utils



import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.assessment.model.PostResponse
import com.example.assessment.repo.PAGE_SIZE

import com.example.assessment.retrofit.ApiService
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class PostsPagingSource(private val postsApi: ApiService) : PagingSource<Int, PostResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostResponse> {

        val page = params.key ?: STARTING_PAGE_INDEX
        val startTime: Long = System.currentTimeMillis()
        return try {
            val response = postsApi.postList(page = page, PAGE_SIZE)
            Log.d(
                "TIMING",
                "TASK took :   ${((System.currentTimeMillis() - startTime))} millisecond"
            )
            LoadResult.Page(
                data = response,
                nextKey = if (response.isEmpty()) null else page + 1,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }


    override fun getRefreshKey(state: PagingState<Int, PostResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
