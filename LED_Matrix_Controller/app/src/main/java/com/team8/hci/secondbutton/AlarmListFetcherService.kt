package com.team8.hci.secondbutton

import android.content.Intent
import android.app.IntentService

class AlarmListFetcherService: IntentService(AlarmListFetcherService::class.simpleName) {

    override fun onCreate() {
        super.onCreate()
    }
    override fun onHandleIntent(workIntent: Intent) {
        // Gets data from the incoming Intent

    }
}