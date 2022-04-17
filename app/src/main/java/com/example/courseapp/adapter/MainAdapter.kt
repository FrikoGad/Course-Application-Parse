package com.example.courseapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.courseapp.R
import com.example.courseapp.model.CurrencyItem
import kotlinx.android.synthetic.main.item_list_layout.view.*


class MainAdapter: RecyclerView.Adapter<MainAdapter.StartViewHolder>() {

    var listCurrency = emptyList<CurrencyItem>()

    class StartViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_layout, parent, false)
        return StartViewHolder(view)
    }

    override fun onBindViewHolder(holder: StartViewHolder, position: Int) {
        holder.itemView.item_currency.text = listCurrency[position].currency
        holder.itemView.item_code.text = listCurrency[position].code
        holder.itemView.item_course.text = listCurrency[position].course
        holder.itemView.item_units.text = listCurrency[position].units
    }

    override fun getItemCount(): Int {
        return listCurrency.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<CurrencyItem>) {
        listCurrency = list
        notifyDataSetChanged()
    }
}