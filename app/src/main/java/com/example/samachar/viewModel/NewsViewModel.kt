package com.example.samachar.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.example.samachar.model.Article
import com.example.samachar.model.NewsResponse
import com.example.samachar.repository.NewsRepository
import com.example.samachar.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingPageNumber = 1
//    var breakingNewsResponse: NewsResponse? = null

//    //for searching news
//    val searchNews: MutableLiveData<Resource<NewsResponse>?> = MutableLiveData()
//    var searchPageNumber = 1
//    var searchNewsResponse: NewsResponse? = null

//    lateinit var articles: LiveData<PagingData<Article>>

    init {
        getBreakingNews("in")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {

        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingPageNumber)
        breakingNews.postValue(handleBreakingNewsResponse(response))

    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {

        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
//                breakingPageNumber++
//                if (breakingNewsResponse == null) {
//                    breakingNewsResponse = resultResponse
//                } else {
//                    val oldArticles = breakingNewsResponse?.articles
//                    val newArticles = resultResponse.articles
//                    oldArticles?.addAll(newArticles)
//                }
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

//    fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>? {
//        if (response.isSuccessful) {
//            response.body()?.let { resultResponse ->
//                searchPageNumber++
//                if (searchNewsResponse == null) {
//                    searchNewsResponse = resultResponse
//                } else {
//                    val oldArticles = searchNewsResponse?.articles
//                    val newArticles = resultResponse.articles
//                    oldArticles?.addAll(newArticles)
//                }
//                return Resource.Success(searchNewsResponse?: resultResponse)
//            }
//        }
//        return Resource.Error(response.message())
//    }
//
//    fun getSearchedNews(queryString: String) = viewModelScope.launch {
//        searchNews.postValue(Resource.Loading())
//        val response = newsRepository.getSearchNews(queryString, searchPageNumber)
//        searchNews.postValue(handleSearchNewsResponse(response))
//    }
//
//    fun insertArticle(article: Article) = viewModelScope.launch {
//        newsRepository.insert(article)
//    }
//    fun deleteArticle(article: Article) = viewModelScope.launch {
//        newsRepository.delete(article)
//    }
//    fun getSavedArticles() = newsRepository.getAllArticles()
//
//    fun getBreakingNews() : LiveData<PagingData<Article>>{
//        return articles
//    }
}