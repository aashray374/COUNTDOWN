package com.example.countdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var countDownTimer: CountDownTimer
    private var isTimerRunning = false
    private val duration = 300 * 1000 // 300 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val day:TextView=findViewById(R.id.days)
        val hour: TextView = findViewById(R.id.hours)
        val min: TextView = findViewById(R.id.minutes)
        //val sec: TextView = findViewById(R.id.seconds)


      fetchSystemDateTime()
    }


    private fun fetchSystemDateTime() {
        val currentTimeMillis = System.currentTimeMillis()
        val targetDateString = "2024-01-12T23:59:59" // Replace with your target date
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val targetDate = sdf.parse(targetDateString)
        val timeDifferenceMillis = targetDate.time - currentTimeMillis

        startCountdown(timeDifferenceMillis)
    }


    private fun startCountdown(timeinMillis:Long) {
        countDownTimer = object : CountDownTimer(timeinMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val days =TimeUnit.MILLISECONDS.toDays((millisUntilFinished))
                val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)%24
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
                //val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60

                // Format the time values into a string
                val countdownText = String.format(
                    "%02d:%02d:%02d",
                    days, hours,minutes,
                )

                // Update the UI
                updateUI(countdownText)
            }

            override fun onFinish() {
                // When the countdown is finished, update UI and show a message
                updateUI("00:00:00")

            }
        }

        // Start the countdown timer
        countDownTimer.start()
        isTimerRunning = true

    }




    private fun updateUI(countdownText: String) {
        val hourMinSec: List<String> = countdownText.split(":")
        findViewById<TextView>(R.id.hours).text = hourMinSec[1]
        findViewById<TextView>(R.id.minutes).text = hourMinSec[2]
        findViewById<TextView>(R.id.days).text = hourMinSec[0]
    }

    private fun resetUI() {
        findViewById<TextView>(R.id.days).text = "00"
        findViewById<TextView>(R.id.hours).text = "00"
        findViewById<TextView>(R.id.minutes).text = "00"
        isTimerRunning = false

    }
}
