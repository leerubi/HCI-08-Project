package com.team8.hci.secondbutton

import android.app.Application

class App : Application() {

    // prefs라는 이름의 MySharedPreferences 하나만 생성할 수 있도록 설정
    companion object {
        lateinit var prefs : MySharedPreferences
    }
    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        super.onCreate()
    }
}