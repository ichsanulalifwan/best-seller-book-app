package com.dicoding.thenewyorktimesapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.thenewyorktimesapp.core.R
import com.dicoding.thenewyorktimesapp.core.databinding.ItemListBookBinding
import com.dicoding.thenewyorktimesapp.core.domain.model.Book

class BookAdapter : RecyclerView.Adapter<BookAdapter.ListViewHolder>() {

    private var listData = ArrayList<Book>()
    var onItemClick: ((Book) -> Unit)? = null

    fun setData(newListData: List<Book>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_book, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBookBinding.bind(itemView)
        fun bind(data: Book) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.bookImage)
                    .into(imgCover)
                tvTitle.text = data.title
                tvAuthor.text = data.author
                tvDescription.text = data.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}