package com.ares.mathquizz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ares.mathquizz.databinding.ActivityRankBinding

private lateinit var binding: ActivityRankBinding
class RankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var helper = Database(applicationContext)
        val db = helper.readableDatabase
        var rs = db.rawQuery("select * from users", null)
        val phoneNumber = intent.getStringExtra("PHONE_NUMBER")

        var found = false
        var score: String? = null

        while (rs.moveToNext()) {
            val phoneNumberFromDB = rs.getString(1)
            val scoreFromDB = rs.getString(3)

            if (phoneNumber == phoneNumberFromDB) {
                found = true
                score = scoreFromDB
            }
        }

        if (found) {
            binding.txtScore.setText(score)
        } else {
            binding.txtScore.setText("not found")
        }

        rs.close()
        db.close()

        back()
    }

    private fun back() {
        binding.btnBack.setOnClickListener {
            val phoneNumber = intent.getStringExtra("PHONE_NUMBER")
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("PHONE_NUMBER", phoneNumber)
            startActivity(intent)
        }
    }
}