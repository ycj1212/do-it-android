package com.example.doitmission22

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Book(var title: String, var author: String, var contents: String)
class BookAdapter : RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    var items = ArrayList<Book>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textview)
        var textView2: TextView = itemView.findViewById(R.id.textview2)

        fun setItem(item: Book) {
            textView.text = item.title
            textView2.text = item.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.book_information_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    fun addItem(item: Book) {
        items.add(item)
    }
}