import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notif.R
import android.app.Notification
import android.view.View
import androidx.annotation.RequiresApi
import com.example.notif.FirstFragment
import java.lang.Exception

class NotifyDemoActivity : AppCompatActivity() {

    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager =
            getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(
            "com.example.notif",
            "NotifyDemo News",
            "Example News Channel")
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun sendNotification(view: FirstFragment) {

        val notificationID = 101

        val channelID = "com.example.nofit"
        try {
            val notification = Notification.Builder(this@NotifyDemoActivity,
                channelID)
                .setContentTitle("Example Notification")
                .setContentText("This is an  example notification.")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setChannelId(channelID)
                .build()

            notificationManager?.notify(notificationID, notification)

        }
        catch (e : Exception)
        {
           var message = e.message
        }



    }

    private fun createNotificationChannel(id: String, name: String,
                                          description: String) {

        val importance = NotificationManager.IMPORTANCE_LOW

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(id, name, importance)
            channel.description = description
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager?.createNotificationChannel(channel)
        }

    }
}