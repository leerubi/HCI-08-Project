package com.team8.hci.secondbutton

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.support.v4.app.NotificationManagerCompat




class ApplicationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_list)

        // 알람 정보 퍼미션 받아오기.
        val isPermissionAllowed = isNotiPermissionAllowed()
        if (!isPermissionAllowed) {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            startActivity(intent)
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

    private fun isNotiPermissionAllowed(): Boolean {
        val notiListenerSet = NotificationManagerCompat.getEnabledListenerPackages(this)
        val myPackageName = packageName

        for (packageName in notiListenerSet) {
            if (packageName == null) {
                continue
            }
            if (packageName == myPackageName) {
                return true
            }
        }

        return false
    }

    fun startKeywordAlarmActivity() {
        val intent = Intent(this@ApplicationListActivity, KeywordAlarmActivity::class.java)
        startActivity(intent)
    }

}
