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

//        val day1:TextView=findViewById(R.id.days1)
//        val hour1: TextView = findViewById(R.id.hours1)
//        val min1: TextView = findViewById(R.id.minutes1)
//        val day2:TextView=findViewById(R.id.days2)
//        val hour2: TextView = findViewById(R.id.hours2)
//        val min2: TextView = findViewById(R.id.minutes2)

        //val sec: TextView = findViewById(R.id.seconds)


      fetchSystemDateTime()
    }


    private fun fetchSystemDateTime() {
        val currentTimeMillis = System.currentTimeMillis()
        val targetDateString = "2024-01-11T23:59:59" // Replace with your target date
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
        findViewById<TextView>(R.id.hours1).text = (hourMinSec[1].toInt() / 10).toString()
        findViewById<TextView>(R.id.minutes1).text = (hourMinSec[2].toInt() / 10).toString()
        findViewById<TextView>(R.id.days1).text = (hourMinSec[0].toInt() / 10).toString()
        findViewById<TextView>(R.id.hours2).text = (hourMinSec[1].toInt() % 10).toString()
        findViewById<TextView>(R.id.minutes2).text = (hourMinSec[2].toInt() % 10).toString()
        findViewById<TextView>(R.id.days2).text = (hourMinSec[0].toInt() % 10).toString()




    }

    private fun resetUI() {
        findViewById<TextView>(R.id.days1).text = "00"
        findViewById<TextView>(R.id.hours1).text = "00"
        findViewById<TextView>(R.id.minutes1).text = "00"
        isTimerRunning = false

    }
}
