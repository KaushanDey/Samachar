package com.example.samachar.repository.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.samachar.model.Article
import com.example.samachar.model.NewsResponse
import com.example.samachar.repository.service.RetrofitClient
import com.example.samachar.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArticleDataSource(val source: CoroutineScope): PageKeyedDataSource<Int, Article>() {
    //for breaking news
    val breakingNews:MutableLiveData<MutableList<Article>> = MutableLiveData()
    var breakingPageNumber = 1
    var breakingNewsResponse:NewsResponse? = null
    //for searching news
    val searchNews:MutableLiveData<MutableList<Article>> = MutableLiveData()
    var searchPageNumber = 1
    var searchNewsResponse:NewsResponse? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        GlobalScope.launch {
            try{
                val response = RetrofitClient.api.getBreakingNews("in",1,Constants.API_KEY)
                when{
                    response.isSuccessful -> {
                        response.body()?.articles?.let {
                            breakingNews.postValue(it)
                            callback.onResult(it,null,2)
                        }
                    }
                }
            }catch (exception:Exception){
                Log.e("DataSource:: ", exception.message.toString())
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

        try{
            GlobalScope.launch {
                val response = RetrofitClient.api.getBreakingNews("in",params.requestedLoadSize,Constants.API_KEY)
                when{
                    response.isSuccessful -> {
                        response.body()?.articles?.let {
                            callback.onResult(it,params.key+1)
                        }
                    }
                }
            }
        }catch (exception:Exception){
            Log.e("DataSource:: ", exception.message.toString())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
    }
}