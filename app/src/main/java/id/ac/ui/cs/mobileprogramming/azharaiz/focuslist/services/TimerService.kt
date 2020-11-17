package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.services

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.CountDownTimer
import android.os.IBinder
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.helpers.TimerHelper

class TimerService : Service() {

    private lateinit var timer: CountDownTimer
    private var timeDurationInSeconds: Long = 0
    private var timeRemainingInSeconds: Long = 0

    private val broadCastIntent: Intent = Intent()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val intentValue: Int = intent?.getIntExtra("TIME_VALUE", 0) ?: 0
        timeDurationInSeconds = intentValue.toLong()
        timeRemainingInSeconds = intentValue.toLong()
        startTimer()
        registerAllReceiver()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun registerAllReceiver() {
        registerPauseReceiver()
        registerResumeIntent()
    }

    private fun registerResumeIntent() {
        val resumeIntent = IntentFilter()
        resumeIntent.addAction("RESUME")

        val resumeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                timer.cancel()
                startTimer()
            }
        }
        registerReceiver(resumeReceiver, resumeIntent)
    }

    private fun registerPauseReceiver() {
        val pauseIntent = IntentFilter()
        pauseIntent.addAction("PAUSE")

        val pauseReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                timer.cancel()
            }
        }
        registerReceiver(pauseReceiver, pauseIntent)
    }

    override fun onDestroy() {
        timer.cancel()
        broadCastIntent.action = "STOP"
        sendBroadcast(broadCastIntent)
        super.onDestroy()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeRemainingInSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemainingInSeconds = millisUntilFinished / 1000
                broadcastTimerIntent(timeRemainingInSeconds.toInt())
            }

            override fun onFinish() {
                broadCastIntent.action = "FINISHED"
                broadCastIntent.putExtra(
                    "PREVIOUS_DURATION",
                    TimerHelper.displayTimer(timeDurationInSeconds.toInt())
                )
                sendBroadcast(broadCastIntent)
            }
        }.start()
    }

    private fun broadcastTimerIntent(timeRemaining: Int) {
        broadCastIntent.action = "COUNTER"
        broadCastIntent.putExtra("TIME_REMAINING", timeRemaining)
        sendBroadcast(broadCastIntent)
    }
}