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


class ApplicationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_list)

//        // alarmOnAppsList를 저장할 Shared Preferences
//        val pref = this.getPreferences(0)
//        val editor = pref.edit()
        val alarmOnAppsList = ArrayList<String>()

        // 앱의 스위치를 On하면 앱 이름을 리스트에 저장 / Off하면 앱 이름을 리스트에서 삭제
        appSwitch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                alarmOnAppsList.add(appName1.text.toString())
            } else {
                alarmOnAppsList.remove(appName1.text.toString())
            }

            App.prefs.myEditText = alarmOnAppsList.toString()
            Log.i("alarmOnAppsList", App.prefs.myEditText)
        }

        appSwitch2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                alarmOnAppsList.add(appName2.text.toString())
            } else {
                alarmOnAppsList.remove(appName2.text.toString())
            }

            App.prefs.myEditText = alarmOnAppsList.toString()
            Log.i("alarmOnAppsList", App.prefs.myEditText)
        }

        appSwitch3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                alarmOnAppsList.add(appName3.text.toString())
            } else {
                alarmOnAppsList.remove(appName3.text.toString())
            }

            App.prefs.myEditText = alarmOnAppsList.toString()
            Log.i("alarmOnAppsList", App.prefs.myEditText)
//            editor.putString("AlarmOnApps", alarmOnAppsList.toString()).apply()
//            Log.i("AlarmOnApps", pref.getString("AlarmOnApps", "없습니다"))
        }

        // 키워드 알림 텍스트를 누르면 KeywordAlarmActivity로 전환.
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

    private fun startKeywordAlarmActivity() {
        val intent = Intent(this@ApplicationListActivity, KeywordAlarmActivity::class.java)
        startActivity(intent)
    }

}
