package com.example.notif

import android.app.Application;
import com.onesignal.OneSignal
class ApplicationClass : Application() {
    val ONESIGNAL_APP_ID = "746f2a43-1e36-4cdc-9d51-1e15020724db"
    override fun onCreate() {
        super.onCreate()
        //OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}