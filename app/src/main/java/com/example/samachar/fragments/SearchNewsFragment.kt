package com.example.samachar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samachar.NewsActivity
import com.example.samachar.R
import com.example.samachar.adapters.ArticleAdapter
import com.example.samachar.utils.Constants
import com.example.samachar.utils.Resource
import com.example.samachar.utils.shareNews
import com.example.samachar.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

//    lateinit var viewModel: NewsViewModel
//    lateinit var newsAdapter : ArticleAdapter
//    val TAG = "SearchNewsFragment"
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel = (activity as NewsActivity).viewModel
//
//        setupRecyclerView()
//        setViewModelObserver()
//
//        var searchJob : Job? = null
//        etSearch.addTextChangedListener{editable ->
//            searchJob?.cancel()
//            searchJob  = MainScope().launch {
//                delay(Constants.SEARCH_TIME_DELAY)
//                editable?.let{
//                    if(!editable.toString().trim().isEmpty()){
//                        viewModel.getSearchedNews(editable.toString())
//                    }
//                }
//            }
//        }
//
//    }
//
//    private fun setViewModelObserver() {
//
//        viewModel.searchNews.observe(viewLifecycleOwner, Observer { newsResponse ->
//            when(newsResponse){
//                is Resource.Success -> {
//                    shimmerFrameLayout3.stopShimmerAnimation()
//                    shimmerFrameLayout3.visibility = View.GONE
//                    newsResponse.data?.let { news->
//                        newsAdapter.differ.submitList(news.articles)
//                    }
//                }
//
//                is Resource.Error -> {
//                    shimmerFrameLayout3.stopShimmerAnimation()
//                    shimmerFrameLayout3.visibility = View.GONE
//                    newsResponse.message?.let { message ->
//                        Log.e(TAG,"Error :: $message")
//                    }
//                }
//
//                is Resource.Loading -> {
//                    shimmerFrameLayout3.startShimmerAnimation()
//                    shimmerFrameLayout3.visibility = View.VISIBLE
//                }
//
//                null -> TODO()
//            }
//        })
//
//    }
//
//    private fun setupRecyclerView() {
//
//        newsAdapter = ArticleAdapter()
//        rvSearchNews.apply {
//            adapter = newsAdapter
//            layoutManager = LinearLayoutManager(activity)
//        }
//
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
//
//            viewModel.insertArticle(it)
//            Snackbar.make(requireView(),"Saved", Snackbar.LENGTH_SHORT)
//                .show()
//        }
//        newsAdapter.onDeleteClickListener {
//            viewModel.deleteArticle(it)
//            Snackbar.make(requireView(),"Removed", Snackbar.LENGTH_SHORT)
//                .show()
//        }
//        newsAdapter.onShareClickListener{
//            shareNews(context,it)
//        }
//    }
//
//
//

}