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
import android.support.design.widget.TextInputEditText
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView


class KeywordAlarmActivity : AppCompatActivity() {
    lateinit var AppState : App
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppState = applicationContext as App
        setContentView(R.layout.activity_keyword_alarm)

 //       Log.i("여기보세요", App.prefs2.keywordSwitchEditText)

        // 키워드 알림이 On인지 Off인지 불러와서 텍스트 설정
        keywordSwitch.isChecked = App.prefs2.keywordSwitchEditText.equals("켜짐")

        Log.i("keywordSwitchOnOff", App.prefs2.keywordSwitchEditText)
        Log.i("alarmOnAppsList", App.prefs.appListEditText)

        // 키워드 알림 On/Off 정보를 text로 저장
        keywordSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                App.prefs2.keywordSwitchEditText = "켜짐"
            } else {
                App.prefs2.keywordSwitchEditText = "꺼짐"
            }
        }
        val KeywordAddbutton = findViewById<TextView>(R.id.KeywordAdd)
        val KeywordField = findViewById<EditText>(R.id.editText1)
        KeywordAddbutton.setOnClickListener()
        {
            var Keyword = KeywordField.text as String
            if(Keyword != "") {
                AppState.AddKeyword(Keyword)
                Toast.makeText(this, Keyword + "가 키워드 리스트에 추가되었습니다!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        // 키워드 알림이 On인지 Off인지 불러와서 텍스트 설정
        if (App.prefs2.keywordSwitchEditText.equals("켜짐")) {
            keywordSwitch.isChecked = true
        } else {
            keywordSwitch.isChecked = false
        }
    }

//    fun onNotificationPosted(sbn: StatusBarNotification) {
//        Log.i("NotificationListener", "[snowdeer] onNotificationPosted() - " + sbn.toString())
//        Log.i("NotificationListener", "[snowdeer] PackageName:" + sbn.packageName)
//        Log.i("NotificationListener", "[snowdeer] PostTime:" + sbn.postTime)
//
//        val notificatin = sbn.notification
//        val extras = notificatin.extras
//        val title = extras.getString(Notification.EXTRA_TITLE)
//        val smallIconRes = extras.getInt(Notification.EXTRA_SMALL_ICON)
//        val largeIcon = extras.getParcelable(Notification.EXTRA_LARGE_ICON) as Bitmap
//        val text = extras.getCharSequence(Notification.EXTRA_TEXT)
//        val subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT)
//
//        Log.i("NotificationListener", "[snowdeer] Title:" + title!!)
//        Log.i("NotificationListener", "[snowdeer] Text:" + text!!)
//        Log.i("NotificationListener", "[snowdeer] Sub Text:" + subText!!)
//    }
//
//    fun onNotificationRemoved(sbn: StatusBarNotification) {
//        Log.i("NotificationListener", "[snowdeer] onNotificationRemoved() - " + sbn.toString())
//    }

}
