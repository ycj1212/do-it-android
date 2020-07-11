package com.example.doitmisstion13

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Client(var name: String, var birth: String, var phone: String)

class ClientAdapter : RecyclerView.Adapter<ClientAdapter.ViewHolder>() {
    var items: ArrayList<Client> = ArrayList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textview01: TextView? = null
        var textview02: TextView? = null
        var textview03: TextView? = null

        init {
            textview01 = itemView.findViewById(R.id.textview01)
            textview02 = itemView.findViewById(R.id.textview02)
            textview03 = itemView.findViewById(R.id.textview03)
        }

        fun setItem(item: Client) {
            textview01?.text = item.name
            textview02?.text = item.birth
            textview03?.text = item.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = inflater.inflate(R.layout.client_information, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Client = items[position]
        holder.setItem(item)
    }

    fun addItem(item: Client) {
        items.add(item)
    }
}