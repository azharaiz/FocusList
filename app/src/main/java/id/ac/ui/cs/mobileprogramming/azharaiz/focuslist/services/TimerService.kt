package id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.R
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.data.TimerLog
import id.ac.ui.cs.mobileprogramming.azharaiz.focuslist.viewmodels.TimerLogViewModel

class TimerService : Service() {

    private lateinit var timer: CountDownTimer
    private var timeDurationInSeconds: Long = 0
    private var timeRemainingInSeconds: Long = 0
    private var timerStatus: TimerStatus = TimerStatus.Stopped

    companion object {
        private const val CHANNEL_ID = "focuslist_timer"
        private const val NOTIFICATION_ID = 170
    }

    private lateinit var mTimerLogViewModel: TimerLogViewModel
    private lateinit var context: Context

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

    override fun onCreate() {
        context = applicationContext
        super.onCreate()
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

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "TIMER_NOTIFICATION"
            val descriptionText = "Channel for FocusList timer"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(getString(R.string.app_name))
            .setContentText("Time's Up!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID, builder.build())
        }
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
        createNotificationChannel()
        sendNotification()
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