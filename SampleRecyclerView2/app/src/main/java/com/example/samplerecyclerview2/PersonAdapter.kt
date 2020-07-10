package com.example.samplerecyclerview2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.ViewHolder>(), OnPersonItemClickListener {
    private var items: ArrayList<Person> = ArrayList<Person>()
    var listener: OnPersonItemClickListener? = null

    class ViewHolder(itemView: View, listener: OnPersonItemClickListener) : RecyclerView.ViewHolder(itemView) {
        private var textView: TextView? = null
        private var textView2: TextView? = null

        init {
            textView = itemView.findViewById(R.id.textView)
            textView2 = itemView.findViewById(R.id.textView2)

            itemView.setOnClickListener {
                val position = adapterPosition

                listener.onItemClick(this@ViewHolder, it, position)
            }
        }

        fun setItem(item: Person) {
            textView?.text = item.name
            textView2?.text = item.mobile
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = inflater.inflate(R.layout.person_item, parent, false)

        return ViewHolder(itemView, this)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Person = items[position]
        holder.setItem(item)
    }

    override fun onItemClick(holder: ViewHolder, view: View, position: Int) {
        listener?.onItemClick(holder, view, position)
    }

    fun setOnItemClickListener(listener: OnPersonItemClickListener) {
        this.listener = listener
    }

    fun addItem(item: Person) {
        items.add(item)
    }

    fun setItems(items: ArrayList<Person>) {
        this.items = items
    }

    fun getItem(position: Int): Person {
        return items[position]
    }

    fun setItem(position: Int, item: Person) {
        items[position] = item
    }
}