package com.team8.hci.secondbutton

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    // 파일 이름과 appListEditText를 저장할 Key 값을 만들고 prefs 인스턴스 초기화
    val prefs: SharedPreferences = context.getSharedPreferences("alarmOnAppsList", 0)

    // get/set 함수 임의 설정. get 실행 시 저장된 값을 반환하며 default 값은 "없습니다"
    // set(value) 실행 시 value로 값을 대체한 후 저장
    var appListEditText: String
        get() = prefs.getString("alarmOnAppsList", "없습니다")
        set(value) = prefs.edit().putString("alarmOnAppsList", value).apply()


    // keywordSwitch checked 여부를 저장할 Key 값을 만들고 prefs2 인스턴스 초기화
    val prefs2: SharedPreferences = context.getSharedPreferences("keywordSwitchOnOff", 0)

    // get/set 함수 임의 설정. get 실행 시 저장된 값을 반환하며 default 값은 "없습니다"
    // set(value) 실행 시 value로 값을 대체한 후 저장
    var keywordSwitchEditText: String
        get() = prefs2.getString("keywordSwitchOnOff", "")
        set(value) = prefs2.edit().putString("keywordSwitchOnOff", value).apply()
}