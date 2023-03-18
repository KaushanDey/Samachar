package com.example.samachar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.util.Util
import com.example.samachar.R
import com.example.samachar.databinding.ItemArticleBinding
import com.example.samachar.model.Article
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_article,
        parent,false))

    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticle)
            tvSource.text = article.source.name
            tvArticleTitle.text = article.title
            tvPublished.text = article.publishedAt
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }

        //item click listener
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                article.let { article ->
                    it(article)
                }
            }
        }
        holder.itemView.ivShare.setOnClickListener {
            onShareNewsClick?.let {
                article.let { it1 ->
                    it(it1)
                }
            }
        }
        holder.itemView.ivSave.setOnClickListener {
            if(holder.itemView.ivSave.tag.toString().toInt() == 0){
                holder.itemView.ivSave.tag = 1
                holder.itemView.ivSave.setImageDrawable(it.resources.getDrawable(R.drawable.ic_saved))
                onArticleSaveClick?.let {
                    if(article != null){
                        it(article)
                    }
                }
            }else{
                holder.itemView.ivSave.tag = 0
                holder.itemView.ivSave.setImageDrawable(it.resources.getDrawable(R.drawable.ic_save))
                onArticleSaveClick?.let {
                    if(article != null){
                        it(article)
                    }
                }
            }
            onArticleSaveClick?.let {
                article?.let { it1 -> it(it1) }
            }
        }
    }

    var isSave = false

    override fun getItemId(position: Int) = position.toLong()

    private var onItemClickListener: ((Article) -> Unit)? = null
    private var onArticleSaveClick: ((Article) -> Unit)? = null
    private var onArticleDeleteClick: ((Article) -> Unit)? = null
    private var onShareNewsClick: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }

    fun onSaveClickListener(listener: (Article) -> Unit){
        onArticleSaveClick = listener
    }

    fun onDeleteClickListener(listener: (Article) -> Unit){
        onArticleDeleteClick = listener
    }

    fun onShareClickListener(listener: (Article) -> Unit){
        onShareNewsClick = listener
    }
}