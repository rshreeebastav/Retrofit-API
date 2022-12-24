package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
        searchUser()
    }
private fun searchUser(){
    search_button.setOnClickListener {
        if(!TextUtils.isEmpty(SearchEditText.text.toString()))
        {
   viewModel.searchUser(SearchEditText.text.toString())
        }else{
            viewModel.getUsersList()
        }
    }
}
    private fun initRecyclerView() {
        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter

        }

    }

    fun initViewModel(){
      viewModel =   ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUserListObserverable().observe(this, Observer {
            if( it == null){
                Toast.makeText(this@MainActivity,"No results Found",Toast.LENGTH_LONG).show()
            }else{
                recyclerViewAdapter.userList =it.data.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getUsersList()
    }
}