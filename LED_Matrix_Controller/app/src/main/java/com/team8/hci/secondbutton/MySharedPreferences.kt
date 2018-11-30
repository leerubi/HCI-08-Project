package com.team8.hci.secondbutton

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    // 파일 이름과 EditText를 저장할 Key 값을 만들고 prefs 인스턴스 초기화
    val PREFS_FILENAME = "prefs"
    val PREF_KEY_MY_EDITTEXT = "myEditText"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    // get/set 함수 임의 설정. get 실행 시 저장된 값을 반환하며 default 값은 "없습니다"
    // set(value) 실행 시 value로 값을 대체한 후 저장
    var myEditText: String
        get() = prefs.getString(PREF_KEY_MY_EDITTEXT, "없습니다")
        set(value) = prefs.edit().putString(PREF_KEY_MY_EDITTEXT, value).apply()

}