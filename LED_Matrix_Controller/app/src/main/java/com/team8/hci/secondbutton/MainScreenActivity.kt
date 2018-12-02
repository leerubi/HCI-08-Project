package com.team8.hci.secondbutton

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

import android.widget.ImageButton

class MainScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity","Started!")
        //알람 정보를 받아오는 서비스를 실행합니다. 에뮬레이터는 작동 불가라서 주석 처리.
//        val NotificationListenerintent = Intent(this,NotificationListener::class.java)
//        startService(NotificationListenerintent)

        val applistbutton = findViewById<TextView>(R.id.AppConfig)
        applistbutton.setOnClickListener {
            StartAppListActivity()
        }

    }

    private fun StartAppListActivity()
    {
        val intent = Intent(this, ApplicationListActivity::class.java)
        startActivity(intent)
    }



}