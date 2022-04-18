package com.example.courseapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.courseapp.adapter.MainAdapter
import com.example.courseapp.model.CurrencyItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

class MainViewModel : ViewModel() {

     @SuppressLint("NotifyDataSetChanged")
     fun getWeb(itemList: ArrayList<CurrencyItem>, adapter:MainAdapter) {
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