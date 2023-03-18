package com.example.samachar.repository

import com.example.samachar.model.Article
import com.example.samachar.repository.db.ArticleDatabase
import com.example.samachar.repository.service.RetrofitClient

class NewsRepository(
    val db:ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode:String, pageNumber:Int) =
        RetrofitClient.api.getBreakingNews(countryCode,pageNumber)

    suspend fun getSearchNews(q:String, pageNumber:Int) =
        RetrofitClient.api.getBreakingNews(q,pageNumber)

    suspend fun insert(article: Article) =
        db.getArticleDao().insert(article)

    suspend fun delete(article: Article) =
        db.getArticleDao().deleteArticle(article)

    fun getAllArticles() = db.getArticleDao().getArticles()
}