package com.team8.hci.secondbutton

import android.app.Notification
import android.content.Intent
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.support.v4.app.NotificationManagerCompat
import android.util.Log

class NotificationListener: NotificationListenerService () {

    override fun onCreate() {
        super.onCreate()
        Log.i("NotificationListener", "onCreate()");

        val isPermissionAllowed = isNotiPermissionAllowed()
        if (!isPermissionAllowed) {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            startActivity(intent)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        TODO("not Implemented")
    }
/*
    override fun onBind(intent: Intent?): IBinder? {
        super.onBind(intent)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
*/
    override fun onNotificationPosted(sbn: StatusBarNotification) {

        Log.i("NotificationListener", "PackageName:${sbn.packageName}")
        val notificatin = sbn.getNotification()
        val extras = notificatin.extras
        val title = extras.getString(Notification.EXTRA_TITLE)
        val text = extras.getCharSequence(Notification.EXTRA_TEXT)
        val subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT)
        Log.i("NotificationListener", "Title:$title")
        Log.i("NotificationListener", "Text:$text")
        Log.i("NotificationListener", "Sub Text:$subText")

    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
    }

    override fun onDestroy() {
        Log.i("NotificationListener", "onDestroy()");

        super.onDestroy()

    }

    private fun isNotiPermissionAllowed(): Boolean {
        val notiListenerSet = NotificationManagerCompat.getEnabledListenerPackages(this)
        val myPackageName = packageName

        for (packageName in notiListenerSet) {
            if (packageName == null) {
                continue
            }
            if (packageName == myPackageName) {
                return true
            }
        }

        return false
    }

}