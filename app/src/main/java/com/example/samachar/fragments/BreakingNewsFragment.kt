package com.example.samachar.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samachar.NewsActivity
import com.example.samachar.R
import com.example.samachar.adapters.ArticleAdapter
import com.example.samachar.utils.Resource
import com.example.samachar.utils.shareNews
import com.example.samachar.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlin.random.Random


class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: ArticleAdapter
    val TAG = "BreakingNewsFragment"

    private fun setupRecyclerView(){
        newsAdapter = ArticleAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
//            viewModel.getBreakingNews()
        }

//        newsAdapter.setOnItemClickListener {
//            val bundle = Bundle().apply {
//                putSerializable("article",it)
//            }
//            findNavController().navigate(
//                R.id.action_breakingNewsFragment_to_articleFragment,
//                bundle
//            )
//
//        }
//        newsAdapter.onSaveClickListener {
//            if(it.id == null){
//                it.id = Random.nextInt(0,1000)
//            }
//            viewModel.insertArticle(it)
//            Snackbar.make(requireView(),"Saved",Snackbar.LENGTH_SHORT)
//                .show()
//        }
//        newsAdapter.onDeleteClickListener {
//            viewModel.deleteArticle(it)
//            Snackbar.make(requireView(),"Removed",Snackbar.LENGTH_SHORT)
//                .show()
//        }
//        newsAdapter.onShareClickListener{
//            shareNews(context,it)
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()
        setViewModelObserver()
    }

    private fun setViewModelObserver() {
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer{newsResponse ->
            when(newsResponse){
                is Resource.Success -> {
                    shimmerFrameLayout.stopShimmerAnimation()
                    shimmerFrameLayout.visibility = View.GONE
                    newsResponse.data?.let{ news ->
                        rvBreakingNews.visibility = View.VISIBLE
                        newsAdapter.differ.submitList(news.articles)

                    }
                }

                is Resource.Error -> {
                    shimmerFrameLayout.stopShimmerAnimation()
                    shimmerFrameLayout.visibility = View.GONE
                    newsResponse.message?.let { message ->
                        Log.e(TAG,"Error :: $message")
                    }
                }

                is Resource.Loading -> {
                    shimmerFrameLayout.startShimmerAnimation()
                    shimmerFrameLayout.visibility = View.VISIBLE
                }

            }
        })
    }

}