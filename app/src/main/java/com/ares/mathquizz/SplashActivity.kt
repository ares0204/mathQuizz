package com.ares.mathquizz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Thiết lập thời gian hiển thị của màn hình chào mừng (ví dụ: 2 giây)
        val splashScreenDuration = 2000L
        val handler = android.os.Handler()
        handler.postDelayed({
            // Chuyển đến màn hình tiếp theo
            val intent = Intent(this, AuthenActivity::class.java)
            startActivity(intent)
            finish()
        }, splashScreenDuration)
    }
}