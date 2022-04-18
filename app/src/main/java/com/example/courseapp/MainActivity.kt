package com.example.courseapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.courseapp.adapter.MainAdapter
import com.example.courseapp.model.CurrencyItem
import com.example.courseapp.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity() : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var itemList: ArrayList<CurrencyItem>
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        recyclerView.adapter = adapter
        adapter.setList(itemList)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getWeb(itemList, adapter)
        }
    }

    private fun init () {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView = findViewById(R.id.rv_main)
        adapter = MainAdapter()
        itemList = ArrayList()
    }
}