package com.example.lab2.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab2.databinding.ActivityMainBinding
import com.example.lab2.ui.RssFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "RSS Reader"
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, RssFragment())
            .commit()
    }
}