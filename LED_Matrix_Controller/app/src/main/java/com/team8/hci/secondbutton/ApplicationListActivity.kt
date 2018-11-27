package com.team8.hci.secondbutton

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ApplicationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_list)


        // 키워드 알림 텍스트를 누르면 KeywordAlarmActivity로 전환.
        val keywordName1 = findViewById(R.id.keywordName1) as TextView

        keywordName1.setOnClickListener {
            val i = Intent(this@ApplicationListActivity, KeywordAlarmActivity::class.java)
            startActivity(i)
//            finish()
        }

        val keywordOnOffText1 = findViewById(R.id.keywordOnOffText1) as TextView

        keywordOnOffText1.setOnClickListener {

        }

        val keywordOnOffText2 = findViewById(R.id.keywordOnOffText2) as TextView

        keywordOnOffText2.setOnClickListener {

        }

    }
}
