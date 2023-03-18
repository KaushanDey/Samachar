package com.example.samachar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samachar.NewsActivity
import com.example.samachar.R
import com.example.samachar.adapters.SavedNewsAdapter
import com.example.samachar.utils.shareNews
import com.example.samachar.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_saved_news.*

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {
//
//    lateinit var viewModel: NewsViewModel
//    lateinit var newsAdapter : SavedNewsAdapter
//    val TAG = "SavedNewsFragment"
//
//    private fun setupRecyclerView(){
//        newsAdapter = SavedNewsAdapter()
//        rvSavedNews.apply {
//            adapter = newsAdapter
//            layoutManager = LinearLayoutManager(activity)
//        }
//
//        newsAdapter.setOnItemClickListener {
//            val bundle = Bundle().apply {
//                putSerializable("article",it)
//            }
//            findNavController().navigate(
//                R.id.action_savedNewsFragment_to_articleFragment,
//                bundle
//            )
//
//        }
//
//        newsAdapter.onShareClickListener{
//            shareNews(context,it)
//        }
//        //Swipe to delete
//        val onItemTouchHelper = object : ItemTouchHelper.SimpleCallback(
//            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
//            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//        )
//        {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return true
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val position = viewHolder.bindingAdapterPosition
//                val article = newsAdapter.differ.currentList[position]
//                viewModel.deleteArticle(article)
//
//                Snackbar.make(requireView(),"Deleted Successfully",Snackbar.LENGTH_LONG).apply {
//                    setAction("Undo"){
//                        viewModel.insertArticle(article)
//                    }
//                    show()
//                }
//            }
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel = (activity as NewsActivity).viewModel
//        setupRecyclerView()
//        setViewModelObserver()
//    }
//
//    private fun setViewModelObserver() {
//        viewModel = (activity as NewsActivity).viewModel
//
//        viewModel.getSavedArticles().observe(viewLifecycleOwner, Observer {
//            Log.i(TAG,""+it.size)
//            newsAdapter.differ.submitList(it)
//            rvSavedNews.visibility = View.VISIBLE
//            shimmerFrameLayout2.stopShimmerAnimation()
//            shimmerFrameLayout2.visibility = View.GONE
//        })
//    }
}