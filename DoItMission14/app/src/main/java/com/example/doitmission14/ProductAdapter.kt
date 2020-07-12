package com.example.doitmission14

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Product(var name: String, var price: String, var description: String)
class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    var items: ArrayList<Product> = ArrayList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textview01: TextView? = null
        var textview02: TextView? = null
        var textview03: TextView? = null

        init {
            textview01 = itemView.findViewById(R.id.textview01)
            textview02 = itemView.findViewById(R.id.textview02)
            textview03 = itemView.findViewById(R.id.textview03)
        }

        fun setItem(item: Product) {
            textview01?.text = item.name
            textview02?.text = item.price
            textview03?.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = inflater.inflate(R.layout.product_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Product = items[position]
        holder.setItem(item)
    }

    fun addItem(item: Product) {
        items.add(item)
    }
}