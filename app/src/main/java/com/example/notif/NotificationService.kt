package com.example.notif
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import java.lang.Exception
import java.util.*
import kotlin.random.Random.Default.nextInt


class NotificationService : Service() {
    var timer: Timer? = null
    var timerTask: TimerTask? = null
    var TAG = "Timers"
    var Your_X_SECS = 5
    var context = this
    override fun onBind(arg0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand")
        super.onStartCommand(intent, flags, startId)
        startTimer()
        return START_STICKY
    }

    override fun onCreate() {
        Log.e(TAG, "onCreate")
        context = this
    }

    override fun onDestroy() {
        Log.e(TAG, "onDestroy")
        //stopTimerTask()
        super.onDestroy()
    }

    //we are going to use a handler to be able to run in our TimerTask
    val handler = Handler(Looper.myLooper()!!)
    fun startTimer() {
        timer = Timer()
        initializeTimerTask()
        timer!!.schedule(timerTask, 6000, (Your_X_SECS * 6000).toLong()) //
    }

    fun stopTimerTask() {
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }

    fun initializeTimerTask() {
        timerTask = object : TimerTask() {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun run() {
                var ar = arrayListOf<String>("Recordar que tu esposo te quiere", "Recordar que te ama", "Recuerda que siempre estare contigo")
                //use a handler to run a toast that shows the current timestamp
                handler.post { //TODO CALL NOTIFICATION FUNC
                    try {

                        var CHANNEL_ID = "HEADS_UP_NOTIFICATION";
                        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            CHANNEL_ID = "my_channel_01"
                            val name: CharSequence = "my_channel"
                            val Description = "This is my channel"
                            val importance = NotificationManager.IMPORTANCE_HIGH
                            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
                            mChannel.description = Description
                            mChannel.enableLights(true)
                            mChannel.lightColor = Color.RED
                            mChannel.enableVibration(true)
                            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                            mChannel.setShowBadge(false)
                            notificationManager.createNotificationChannel(mChannel)
                        }
                        var random = (0..2).random()
                        val notification: Notification.Builder = Notification.Builder(context, CHANNEL_ID)
                            .setContentTitle(ar[random])
                            .setContentText(ar[random])
                            .setSmallIcon(R.drawable.ic_stat_name)
                            .setAutoCancel(true)

                        NotificationManagerCompat.from(context).notify(1, notification.build())
                    }
                    catch (e: Exception){
                        var m = e.message
                    }
                }
            }
        }
    }

}
