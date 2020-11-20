package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.TimerLog
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TimerLogViewModel

class TimerService : Service() {

    private lateinit var timer: CountDownTimer
    private var timeDurationInSeconds: Long = 0
    private var timeRemainingInSeconds: Long = 0
    private var timerStatus: TimerStatus = TimerStatus.Stopped

    private lateinit var mTimerLogViewModel: TimerLogViewModel

    private val broadCastIntent: Intent = Intent()
    private val binder = TimerBinder()

    private var todoTitle = ""

    enum class TimerStatus {
        Running, Paused, Stopped
    }

    inner class TimerBinder : Binder() {
        fun getService(): TimerService = this@TimerService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onDestroy() {
        timer.cancel()
        addTimerToLog()
        super.onDestroy()
    }

    fun setDuration(duration: Int) {
        timeDurationInSeconds = duration.toLong() * 60
        timeRemainingInSeconds = duration.toLong() * 60
    }

    fun startTimer() {
        timer = object : CountDownTimer(timeRemainingInSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerStatus = TimerStatus.Running
                timeRemainingInSeconds = millisUntilFinished / 1000
                broadcastTimerIntent(timeRemainingInSeconds.toInt())
            }

            override fun onFinish() {
                addTimerToLog()
                if (timeRemainingInSeconds == 0.toLong()) {
                    stopTimer()
                }
            }
        }.start()
    }

    fun pauseTimer() {
        timer.cancel()
        timerStatus = TimerStatus.Paused
    }

    fun resumeTimer() {
        startTimer()
        timerStatus = TimerStatus.Running
    }

    fun stopTimer() {
        if (timerStatus == TimerStatus.Running) {
            timer.cancel()
        }
        broadcastTimerIntent(timeDurationInSeconds.toInt())
        timerStatus = TimerStatus.Stopped
        stopSelf()
    }

    fun setTodoTitle(title: String) {
        todoTitle = title
    }

    fun getTimerStatus(): TimerStatus {
        return timerStatus
    }

    private fun broadcastTimerIntent(timeRemaining: Int) {
        broadCastIntent.action = "COUNTER"
        broadCastIntent.putExtra("TIME_REMAINING", timeRemaining)
        sendBroadcast(broadCastIntent)
    }

    private fun addTimerToLog() {
        mTimerLogViewModel = TimerLogViewModel(application)
        val timerLog = TimerLog(
            0,
            (timeDurationInSeconds - timeRemainingInSeconds).toInt(),
            todoTitle
        )
        mTimerLogViewModel.insertTimerLog(timerLog)
    }
}