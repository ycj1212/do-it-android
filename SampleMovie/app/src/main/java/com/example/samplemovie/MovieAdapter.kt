package com.example.samplemovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var items = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.movie_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: Movie) { items.add(item) }
    fun setItems(items: ArrayList<Movie>) { this.items = items }
    fun getItem(position: Int): Movie = items[position]

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textView)
        var textView2: TextView = itemView.findViewById(R.id.textView2)

        fun setItem(item: Movie) {
            textView.text = item.movieNm
            textView2.text = "${item.audiCnt} 명"
        }
    }
}