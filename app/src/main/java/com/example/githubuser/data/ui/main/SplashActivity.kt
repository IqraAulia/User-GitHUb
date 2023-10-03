package com.example.githubuser.data.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.githubuser.R
import com.example.githubuser.data.ui.setting.MainViewModel
import com.example.githubuser.data.ui.setting.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {
    private val mainViewModel : MainViewModel by viewModels { ViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val moveActivityIntent = Intent(this,MainActivity::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            delay(splashScreenDuration)
            startActivity(moveActivityIntent)
            finish()
        }
    }
    companion object{
        const val splashScreenDuration = 2000L
    }
}