package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.services

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder

class TimerService : Service() {

    enum class TimerState {
        Stopped, Paused, Running
    }

    private lateinit var timer: CountDownTimer
    private var timeRemainingInSeconds: Long = 0
    private var timerState = TimerState.Stopped

    private val broadCastIntent: Intent = Intent()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val intentValue: Int = intent?.getIntExtra("TIME_VALUE", 0) ?: 0
        timeRemainingInSeconds = intentValue.toLong()
        startTimer()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        timer.cancel()
        broadCastIntent.action = "STOP"
        sendBroadcast(broadCastIntent)
        super.onDestroy()
    }

    private fun startTimer() {
        timerState = TimerState.Running
        timer = object : CountDownTimer(timeRemainingInSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemainingInSeconds--
                broadcastTimerIntent(timeRemainingInSeconds.toInt())
            }

            override fun onFinish() {
                broadCastIntent.action = "STOP"
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