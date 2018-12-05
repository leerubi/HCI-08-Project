package com.team8.hci.secondbutton

import android.app.Application

class App : Application() {

    // prefs~ 라는 이름의 MySharedPreferences를 생성할 수 있도록 설정
    /*
    * prefs: 알람을 받을 앱 리스트
    * prefs2: keywordSwitch On/Off 여부
    * prefs3: 키워드 리스트
    * */
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