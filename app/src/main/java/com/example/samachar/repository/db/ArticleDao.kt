package com.example.samachar.repository.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.samachar.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article):Long

    @Query("Select * from articles")
    fun getArticles() : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}