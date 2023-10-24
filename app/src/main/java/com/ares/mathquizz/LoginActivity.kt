package com.ares.mathquizz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ares.mathquizz.databinding.ActivityLoginBinding

private lateinit var binding: ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phoneNumber = intent.getStringExtra("PHONE_NUMBER")

        var helper = Database(applicationContext)
        val db = helper.readableDatabase
        var rs = db.rawQuery("select * from users", null)

        binding.btnLogin.setOnClickListener {
            while (rs.moveToNext()) {
                val phoneNumberFromDB = rs.getString(1)
                val pinFromDB = rs.getString(2)

                if (phoneNumber == phoneNumberFromDB && binding.edtPIN.text.toString() == pinFromDB) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    break
                }
            }
        }
    }
}