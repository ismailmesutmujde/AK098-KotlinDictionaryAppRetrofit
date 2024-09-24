package com.ismailmesutmujde.kotlindictionaryappretrofit.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ismailmesutmujde.kotlindictionaryappretrofit.R
import com.ismailmesutmujde.kotlindictionaryappretrofit.databinding.ActivityDetailScreenBinding
import com.ismailmesutmujde.kotlindictionaryappretrofit.model.Words

class DetailScreenActivity : AppCompatActivity() {

    private lateinit var bindingDetailScreen : ActivityDetailScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetailScreen = ActivityDetailScreenBinding.inflate(layoutInflater)
        val view = bindingDetailScreen.root
        setContentView(view)

        val word = intent.getSerializableExtra("obj") as Words

        bindingDetailScreen.textViewEnglish2.text = word.english
        bindingDetailScreen.textViewTurkish2.text = word.turkish
    }
}