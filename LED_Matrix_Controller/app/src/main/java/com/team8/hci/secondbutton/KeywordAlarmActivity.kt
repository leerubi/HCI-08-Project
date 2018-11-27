package com.team8.hci.secondbutton

import android.app.Notification
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_application_list.*
import kotlinx.android.synthetic.main.activity_keyword_alarm.*
import android.content.Intent
import android.service.notification.StatusBarNotification
import android.graphics.Bitmap
import android.util.Log


class KeywordAlarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyword_alarm)

        keywordSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Switch on.", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(this, "Switch Off.", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun onNotificationPosted(sbn: StatusBarNotification) {
        Log.i("NotificationListener", "[snowdeer] onNotificationPosted() - " + sbn.toString())
        Log.i("NotificationListener", "[snowdeer] PackageName:" + sbn.packageName)
        Log.i("NotificationListener", "[snowdeer] PostTime:" + sbn.postTime)

        val notificatin = sbn.notification
        val extras = notificatin.extras
        val title = extras.getString(Notification.EXTRA_TITLE)
        val smallIconRes = extras.getInt(Notification.EXTRA_SMALL_ICON)
        val largeIcon = extras.getParcelable(Notification.EXTRA_LARGE_ICON) as Bitmap
        val text = extras.getCharSequence(Notification.EXTRA_TEXT)
        val subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT)

        Log.i("NotificationListener", "[snowdeer] Title:" + title!!)
        Log.i("NotificationListener", "[snowdeer] Text:" + text!!)
        Log.i("NotificationListener", "[snowdeer] Sub Text:" + subText!!)
    }

    fun onNotificationRemoved(sbn: StatusBarNotification) {
        Log.i("NotificationListener", "[snowdeer] onNotificationRemoved() - " + sbn.toString())
    }


}
