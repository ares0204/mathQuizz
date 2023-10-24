package com.ares.mathquizz

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ares.mathquizz.databinding.ActivityRegisterBinding

private lateinit var binding: ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phoneNumber = intent.getStringExtra("PHONE_NUMBER")

        var helper = Database(applicationContext)
        val db = helper.readableDatabase
        var rs = db.rawQuery("select * from users", null)

        binding.btnRegister.setOnClickListener {
            var cv = ContentValues()
            cv.put("userName",phoneNumber)
            cv.put("password", binding.edtRePIN.text.toString())
            db.insert("users",null, cv)

            val intent = Intent(this, AuthenActivity::class.java)
            startActivity(intent)
        }
    }
}