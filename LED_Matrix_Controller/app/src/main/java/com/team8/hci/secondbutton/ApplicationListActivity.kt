package com.team8.hci.secondbutton

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.support.v4.app.NotificationManagerCompat
import android.content.SharedPreferences
import android.app.Activity
import android.R.id.edit
import android.widget.Toast
import android.webkit.JavascriptInterface
import android.R.id.edit
import android.util.Log
import kotlinx.android.synthetic.main.activity_application_list.*
import kotlinx.android.synthetic.main.activity_keyword_alarm.*


class ApplicationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_list)

        // 스위치 On한 앱 이름들을 저장하는 리스트
        val alarmOnAppsList = ArrayList<String>()

        // 키워드 알림이 On인지 Off인지 불러와서 텍스트 설정
        if (App.prefs2.keywordSwitchEditText.equals("켜짐")) {
            keywordOnOffText1.text = "켜짐"
        } else {
            keywordOnOffText1.text = "꺼짐"
        }

        // 키워드 알림이 On인지 Off인지 저장
        App.prefs2.keywordSwitchEditText = keywordOnOffText1.text.toString()
        Log.i("keywordSwitchOnOff", App.prefs2.keywordSwitchEditText)


        // Switch Check Listeners
        // 앱의 스위치를 On하면 앱 이름을 리스트에 저장 / Off하면 앱 이름을 리스트에서 삭제
        appSwitch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                alarmOnAppsList.add(appName1.text.toString())
            } else {
                alarmOnAppsList.remove(appName1.text.toString())
            }

            App.prefs.appListEditText = alarmOnAppsList.toString()
            Log.i("alarmOnAppsList", App.prefs.appListEditText)
        }

        appSwitch2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                alarmOnAppsList.add(appName2.text.toString())
            } else {
                alarmOnAppsList.remove(appName2.text.toString())
            }

            App.prefs.appListEditText = alarmOnAppsList.toString()
            Log.i("alarmOnAppsList", App.prefs.appListEditText)
        }

        appSwitch3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                alarmOnAppsList.add(appName3.text.toString())
            } else {
                alarmOnAppsList.remove(appName3.text.toString())
            }

            App.prefs.appListEditText = alarmOnAppsList.toString()
            Log.i("alarmOnAppsList", App.prefs.appListEditText)
        }

        // 키워드 알림 텍스트를 누르면 KeywordAlarmActivity로 전환
        val keywordName1 = findViewById(R.id.keywordName1) as TextView
        keywordName1.setOnClickListener {
            startKeywordAlarmActivity()
        }

        val keywordOnOffText1 = findViewById(R.id.keywordOnOffText1) as TextView
        keywordOnOffText1.setOnClickListener {
            startKeywordAlarmActivity()
        }

        val keywordOnOffText2 = findViewById(R.id.keywordOnOffText2) as TextView
        keywordOnOffText2.setOnClickListener {
            startKeywordAlarmActivity()
        }


    }

    override fun onResume() {
        super.onResume()

        // 키워드 알림이 On인지 Off인지 불러와서 텍스트 설정
        if (App.prefs2.keywordSwitchEditText.equals("켜짐")) {
            keywordOnOffText1.text = "켜짐"
        } else {
            keywordOnOffText1.text = "꺼짐"
        }
    }


    private fun startKeywordAlarmActivity() {
        val intent = Intent(this@ApplicationListActivity, KeywordAlarmActivity::class.java)
        startActivity(intent)
    }

}
