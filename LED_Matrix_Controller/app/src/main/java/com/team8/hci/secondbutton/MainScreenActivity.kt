package com.team8.hci.secondbutton

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //알람 정보를 받아오는 서비스를 실행합니다.
        //val alarmfetcherintent = Intent(this,AlarmListFetcherService::class.java)
        //startService(alarmfetcherintent)

    }

}