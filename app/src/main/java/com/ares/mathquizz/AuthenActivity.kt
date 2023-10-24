package com.ares.mathquizz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ares.mathquizz.databinding.ActivityAuthenBinding

private lateinit var binding: ActivityAuthenBinding
private var bool = false;
class AuthenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var helper = Database(applicationContext)
        val db = helper.readableDatabase
        var rs = db.rawQuery("select * from users", null)

        binding.btnNext.setOnClickListener {
            while (rs.moveToNext()) {
                val phoneNumberFromDB = rs.getString(1)

                if (binding.edtNumberPhone.text.toString() == phoneNumberFromDB) {
                    login()
                    bool = true
                    break
                }
            }
            if (bool == false) {
                register()
            }
        }
    }

    private fun register() {
        val intent = Intent(this, RegisterActivity::class.java)
        val phoneNumber = binding.edtNumberPhone.text.toString()
        intent.putExtra("PHONE_NUMBER", phoneNumber)
        startActivity(intent)
    }

    private fun login() {
        val intent = Intent(this, LoginActivity::class.java)
        val phoneNumber = binding.edtNumberPhone.text.toString()
        intent.putExtra("PHONE_NUMBER", phoneNumber)
        startActivity(intent)
    }

}