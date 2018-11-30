package com.team8.hci.secondbutton

import android.app.Application

class App : Application() {

    // prefs, pref2라는 이름의 MySharedPreferences를 생성할 수 있도록 설정
    companion object {
        lateinit var prefs : MySharedPreferences
        lateinit var prefs2 : MySharedPreferences
    }
    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        prefs2 = MySharedPreferences(applicationContext)
        super.onCreate()
    }
}