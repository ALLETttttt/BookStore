package com.bookstore.BookDatabase

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.databinding.BookItemBinding



class bookAdapter: RecyclerView.Adapter<bookAdapter.bookHolder>() {
    val bookList = ArrayList<Book>()

    class bookHolder(item: View): RecyclerView.ViewHolder(item) {

        val binding = BookItemBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(book: Book) = with(binding){
            im.setImageResource(R.drawable.photobook)
            booktitle.text = book.title
            bookcost.text = book.cost.toString() + " $"
            bookdesc.text = book.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bookHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return bookHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: bookHolder, position: Int) {
        holder.bind(bookList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addBook(book: Book) {
        bookList.add(book)
        notifyDataSetChanged()
    }

    fun clearList(){
        bookList.clear()
    }
}