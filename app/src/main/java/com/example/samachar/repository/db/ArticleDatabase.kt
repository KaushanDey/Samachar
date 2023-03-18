package com.example.samachar.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.samachar.model.Article


@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase :RoomDatabase() {

    abstract fun getArticleDao() : ArticleDao

    companion object{
        @Volatile
        private var articleDbInstance:ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = articleDbInstance?: synchronized(LOCK){
            articleDbInstance?:createDatabaseInstance(context).also {
                articleDbInstance = it
            }
        }

        private fun createDatabaseInstance(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, ArticleDatabase::class.java,
                "article_db.db"
            ).fallbackToDestructiveMigration()
                .addTypeConverter(Converters::class.java)
                .build()
    }

}