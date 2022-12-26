package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_create_new_user.*

class CreateNewUserActivity : AppCompatActivity() {
    lateinit var viewModel : CreateNewUserActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_user)
        initViewModel()
        createUserObserable()
        createButton.setOnClickListener {
            createUser()
        }
    }
    private fun createUser(){
        val user = User("",editTextName.text.toString(),editTextEmail.text.toString(),editTextGender.text.toString(),editTextStatus.text.toString())
        viewModel.createUser(user)
    }
    private fun initViewModel(){
        viewModel =   ViewModelProvider(this).get(CreateNewUserActivityViewModel::class.java)

    }
    private fun createUserObserable(){
        viewModel.getCreateNewUserObservable().observe(this, Observer {
            if( it == null){
                Toast.makeText(this@CreateNewUserActivity,"Failed to create new user .....", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@CreateNewUserActivity,"Successfully create new user .....", Toast.LENGTH_LONG).show()
                finish()
            }
        })

    }
}