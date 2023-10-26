package com.ares.mathquizz

import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ares.mathquizz.databinding.ActivityQuizBinding
import kotlin.random.Random

private lateinit var binding: ActivityQuizBinding
class QuizActivity: AppCompatActivity() {

    private val random = Random
    private var soA = 0
    private var soB = 0
    private var total = 0
    private var score = 0
    private var currentProcess = 0
    private var isDialogShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTrue.setOnClickListener {
            if (soA + soB == total) {
                score++
                binding.txtScore.setText("Score: " + score.toString())
                play()
            } else {
                currentProcess = 0
            }
        }

        binding.btnFalse.setOnClickListener {
            if (soA + soB != total) {
                score++
                binding.txtScore.setText("Score: " + score.toString())
                play()
            } else {
                currentProcess = 0
            }
        }

        play();
    }

    private fun showDialog() {
        if (!isDialogShown) {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.activity_lose)

            val txtTotalScore = dialog.findViewById(R.id.txtTotalScore) as TextView
            val btnReplay = dialog.findViewById(R.id.btnReplay) as ImageButton
            val btnClose = dialog.findViewById(R.id.btnClose) as ImageButton

            txtTotalScore.text = "Your Score : $score"
            btnReplay.setOnClickListener {
                val intent = Intent(this, QuizActivity::class.java)
                startActivity(intent)
            }
            btnClose.setOnClickListener {
                val phoneNumber = intent.getStringExtra("PHONE_NUMBER")
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("PHONE_NUMBER", phoneNumber)
                startActivity(intent)
            }

            var helper = Database(applicationContext)
            val db = helper.readableDatabase
            var rs = db.rawQuery("select * from users", null)
            val phoneNumber = intent.getStringExtra("PHONE_NUMBER")

            while (rs.moveToNext()) {
                val scoreFromDB = rs.getString(3).toInt()
                val phoneNumberFromDB = rs.getString(1)

                if (phoneNumber == phoneNumberFromDB && scoreFromDB < score) {
                    txtTotalScore.text = "Kỷ lục mới : $score"
                    var cv = ContentValues()
                    cv.put("score",score.toString())
                    db.update("users", cv, "userName = ?", arrayOf(phoneNumber))
                }
            }

            dialog.setCancelable(false)
            dialog.show()
            isDialogShown = true
        }
    }

    private fun play() {
        isDialogShown = false
        soA = random.nextInt(10)
        soB = random.nextInt(10)

        val resultIndex = random.nextInt(2)

        binding.txtA.text = soA.toString()
        binding.txtB.text = soB.toString()
        if (resultIndex == 0) {
            total = random.nextInt(10)
            binding.txtResult.text = total.toString()
        } else {
            total = soA + soB
            binding.txtResult.text = total.toString()
        }
        currentProcess = binding.skbTime.max

        Thread {
            while (currentProcess > 0) {
                runOnUiThread {
                    currentProcess -= 5
                    binding.skbTime.progress = currentProcess
                }
                Thread.sleep(1000)
            }
            runOnUiThread {
                showDialog()
            }
        }.start()
    }
}
