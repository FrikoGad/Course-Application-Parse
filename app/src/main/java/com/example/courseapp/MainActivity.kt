package com.example.courseapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.courseapp.adapter.MainAdapter
import com.example.courseapp.model.CurrencyItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity() : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var itemList: ArrayList<CurrencyItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch {
            getWeb()
        }
        recyclerView = findViewById(R.id.rv_main)
        adapter = MainAdapter()
        itemList = ArrayList()
        recyclerView.adapter = adapter
        adapter.setList(itemList)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getWeb() {
        try {
            val doc = Jsoup.connect("https://www.cbr.ru/currency_base/daily/").get()
            val table  = doc.getElementsByTag("tbody")[0]
            for (i in 1 until table.childrenSize()) {
                val items = CurrencyItem(table
                    .children()[i].child(3).text(),
                    table.children()[i].child(1).text(),
                    table.children()[i].child(2).text(),
                    table.children()[i].child(4).text())
                itemList.add(items)
            }
            CoroutineScope(Dispatchers.Main).launch {
                adapter.notifyDataSetChanged()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}