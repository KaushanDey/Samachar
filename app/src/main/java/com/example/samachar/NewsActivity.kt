package com.example.samachar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.samachar.databinding.ActivityNewsBinding
import com.example.samachar.repository.NewsRepository
import com.example.samachar.repository.db.ArticleDatabase
import com.example.samachar.viewModel.NewsViewModel
import com.example.samachar.viewModel.NewsViewModelFactory
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewsBinding
    lateinit var viewModel:NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProvider = NewsViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(this,viewModelProvider).get(NewsViewModel::class.java)

        binding.bottomNavigationView.setupWithNavController(binding.flFragment.findNavController())


    }
}