package com.example.android.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.android.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Wayne Cockram")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // standard way to inflate view and allocate click listener
        //setContentView(R.layout.activity_main)
        //findViewById<Button>(R.id.done_button).setOnClickListener {
            //addNickName(it)
        //}

        // new way, using binding to find views
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName
        binding.doneButton.setOnClickListener {
            addNickName(it)
        }
    }

    private fun addNickName(view: View) {
        // standard way to access views and set values
        //val editText: EditText = findViewById(R.id.nickname_edit)
        //val nicknameTextView: TextView = findViewById(R.id.nickname_text)

        //nicknameTextView.text = editText.text
        //editText.visibility = View.GONE
        //view.visibility = View.GONE
        //nicknameTextView.visibility = View.VISIBLE

        // accessing views and set values using binding
        binding.apply {
            //nicknameText.text = nicknameEdit.text
            //updated to use data class and data binding
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll() //refresh the ui with new data
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}