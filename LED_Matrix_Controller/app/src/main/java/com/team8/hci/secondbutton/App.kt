package com.team8.hci.secondbutton

import android.app.Application
import android.bluetooth.BluetoothSocket
import android.util.Log

public class App : Application() {

    // prefs~ 라는 이름의 MySharedPreferences를 생성할 수 있도록 설정
    /*
    * prefs: 알람을 받을 앱 리스트
    * prefs2: keywordSwitch On/Off 여부
    * prefs3: 키워드 리스트
    * */


    companion object {
        lateinit var BtSocket : BluetoothSocket
        lateinit var prefs : MySharedPreferences
        lateinit var prefs2 : MySharedPreferences
        lateinit var cur_app : String
        var is_keywordMode = true
    }
    fun setCurrentApp(name : String)
    {
        cur_app = name
    }
    fun getCurrentApp() : String?
    {
        try {
            if (cur_app != null && cur_app != "0")
                return cur_app
        } catch (e: UninitializedPropertyAccessException)
        {
            Log.i("NotificationListener","cur_app has not been initialized")
            return null
        }
        return null
    }

    fun setSocket(soc: BluetoothSocket)
    {
        BtSocket = soc
    }
    fun getSocket() : BluetoothSocket? {
        try {
            if (BtSocket != null)
                return BtSocket
        } catch (e: UninitializedPropertyAccessException)
        {
            Log.i("NotificationListener","BtSocket has not been initialized")
            return null
        }
        return null

    }

    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        prefs2 = MySharedPreferences(applicationContext)
        super.onCreate()
    }
}