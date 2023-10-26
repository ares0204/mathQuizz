package com.ares.mathquizz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ares.mathquizz.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        play()
        rank()
        close()
    }

    private fun rank() {
        binding.btnRank.setOnClickListener {
            val phoneNumber = intent.getStringExtra("PHONE_NUMBER")
            val intent = Intent(this, RankActivity::class.java)
            intent.putExtra("PHONE_NUMBER", phoneNumber)
            startActivity(intent)
        }
    }

    private fun close() {
        binding.btnExit.setOnClickListener {
            finishAffinity()
        }
    }

    private fun play() {
        binding.btnPlay.setOnClickListener {
            val phoneNumber = intent.getStringExtra("PHONE_NUMBER")
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("PHONE_NUMBER", phoneNumber)
            startActivity(intent)
        }
    }
}