package com.ismailmesutmujde.kotlindictionaryappretrofit.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismailmesutmujde.kotlindictionaryappretrofit.R
import com.ismailmesutmujde.kotlindictionaryappretrofit.adapter.WordsRecyclerViewAdapter
import com.ismailmesutmujde.kotlindictionaryappretrofit.dao.WordsDaoInterface
import com.ismailmesutmujde.kotlindictionaryappretrofit.databinding.ActivityMainScreenBinding
import com.ismailmesutmujde.kotlindictionaryappretrofit.model.Words
import com.ismailmesutmujde.kotlindictionaryappretrofit.model.WordsResponse
import com.ismailmesutmujde.kotlindictionaryappretrofit.service.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainScreenActivity : AppCompatActivity(), SearchView.OnQueryTextListener  {

    private lateinit var bindingMainScreen : ActivityMainScreenBinding
    private lateinit var wordsList:ArrayList<Words>
    private lateinit var adapter: WordsRecyclerViewAdapter

    private lateinit var wdi : WordsDaoInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainScreen = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = bindingMainScreen.root
        setContentView(view)

        bindingMainScreen.toolbar.title = "Dictionary Application"
        setSupportActionBar(bindingMainScreen.toolbar)

        bindingMainScreen.recyclerView.setHasFixedSize(true)
        bindingMainScreen.recyclerView.layoutManager = LinearLayoutManager(this)

        wdi = ApiUtils.getWordsDaoInterface()

        /*
        wordsList = ArrayList()
        val w1 = Words(1, "Dog","Köpek")
        val w2 = Words(2, "Fish","Balık")
        val w3 = Words(3, "Table","Masa")

        wordsList.add(w1)
        wordsList.add(w2)
        wordsList.add(w3)
        */

        allWords()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searhWord(query)
        Log.e("Sent Search", query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        searhWord(newText)
        Log.e("As letters are entered", newText)
        return true
    }

    fun allWords() {
        wdi.allWords().enqueue(object : Callback<WordsResponse> {
            override fun onResponse(call: Call<WordsResponse>, response: Response<WordsResponse>) {

                if (response != null) {
                    val wordsList = response.body()!!.words
                    adapter = WordsRecyclerViewAdapter(this@MainScreenActivity, wordsList)
                    bindingMainScreen.recyclerView.adapter = adapter
                }

            }

            override fun onFailure(call: Call<WordsResponse>?, t: Throwable?) {

            }

        })
    }

    fun searhWord(searchingWord:String) {
        wdi.searchWord(searchingWord).enqueue(object : Callback<WordsResponse> {
            override fun onResponse(call: Call<WordsResponse>?, response: Response<WordsResponse>?) {

                if (response != null) {
                    val wordsList = response.body()!!.words
                    adapter = WordsRecyclerViewAdapter(this@MainScreenActivity, wordsList)
                    bindingMainScreen.recyclerView.adapter = adapter
                }

            }

            override fun onFailure(call: Call<WordsResponse>?, t: Throwable?) {

            }

        })
    }
}