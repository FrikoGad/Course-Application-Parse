package com.example.courseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            getWeb()
        }
    }

    private fun getWeb() {
        try {
            val doc = Jsoup.connect("https://www.cbr.ru/currency_base/daily/").get()
            val tables  = doc.getElementsByTag("tbody")
            val table = tables[0]
            val elementsFromTable = table.children()
            val dollar = elementsFromTable[11]
            val dollarElements = dollar.children()
            Log.d("MyLog", "Tbody size: ${dollarElements[3].text()}")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}