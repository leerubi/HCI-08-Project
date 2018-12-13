package com.team8.hci.secondbutton

import android.app.Notification
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import android.widget.Toast
import com.team8.hci.secondbutton.R.id.*
import kotlinx.android.synthetic.main.activity_application_list.*
import java.sql.Types.NULL

class NotificationListener: NotificationListenerService () {

    lateinit var LED_Matrix_socket: BluetoothSocket
    //TODO(지금은 임시로 각 앱에 대한 텍스트를 선언했지만, 추후 변경해야 합니다)
    var KakaoText = "kakao\n"
    var SMSText = "message\n"
    var CallText = "call\n"
    var Text1 = "1\n"
    var Text2 = "2\n"
    var clear = "clear\n"
    lateinit var Appstate: App
    override fun onCreate() {
        super.onCreate()
        Log.i("NotificationListener", "onCreate()")
        val isPermissionAllowed = isNotiPermissionAllowed()
        if (!isPermissionAllowed) {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            startActivity(intent)
            Log.i("NotificationListener", "Start!")
        }
        Appstate = applicationContext as App
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        TODO("not Implemented")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        Log.i("NotificationListener", "Package:${sbn.packageName}")
        val notificatin = sbn.getNotification()
        val extras = notificatin.extras
        val title = extras.getString(Notification.EXTRA_TITLE)
        val text = extras.getCharSequence(Notification.EXTRA_TEXT)
        val subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT)
        Log.i("NotificationListener", "PackageName:${sbn.packageName}")
        Log.i("NotificationListener", "Title:$title")
        Log.i("NotificationListener", "Text:$text")
        Log.i("NotificationListener", "Sub Text:$subText")
        var cur_app = Appstate.getCurrentApp()
        if (Appstate.getSocket() == null) {
            Log.i("NotificationListener", "No Device Connected!")
            return
        } else
            LED_Matrix_socket = Appstate.getSocket() as BluetoothSocket

        if (cur_app != null) {
            if (cur_app in sbn.packageName) {
                LED_Matrix_socket.outputStream.write(clear.toByteArray())
                Appstate.setCurrentApp("0")//Null
            }
        }
        return
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {

        Log.i("NotificationListener", "Package:${sbn.packageName}")
        val notificatin = sbn.getNotification()
        val extras = notificatin.extras
        val title = extras.getString(Notification.EXTRA_TITLE)
        val text = extras.getCharSequence(Notification.EXTRA_TEXT)
        // val subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT)
        Log.i("NotificationListener", "PackageName:${sbn.packageName}")
        Log.i("NotificationListener", "Title:$title")
        Log.i("NotificationListener", "Text:$text")
        //  Log.i("NotificationListener", "Sub Text:$subText")
        var appState = applicationContext as App
        if (appState.getSocket() == null) {
            Log.i("NotificationListener", "No Device Connected!")
            return
        } else
            LED_Matrix_socket = appState.getSocket() as BluetoothSocket

        // 키워드 알림 in all apps (for now)
        //   var is_on = "켜짐"

        if (App.prefs2.keywordSwitchEditText.equals("켜짐")) {
            if (title == null || text == null)
                return
            var keywordlist = appState.getKeywordList()
            if (keywordlist != null) {
                for (K in keywordlist) {
                    if (K in title || K in text) {
                        LED_Matrix_socket.outputStream.write(K.toByteArray())//키워드를 그대로 전송합니다.
                        Toast.makeText(this, "키워드 LED 반짝!", Toast.LENGTH_LONG).show()
                        Appstate.setCurrentApp(sbn.packageName)
                    }
                }
            }
        }
        // 알림 switch on 한 앱에서 알림이 오면 LED 작동
        if ("android.incallui" in sbn.packageName) {
            if ("전화" in App.prefs.appListEditText) {

                LED_Matrix_socket.outputStream.write(CallText.toByteArray())
                Toast.makeText(this, "전화 LED 반짝!", Toast.LENGTH_LONG).show()

                Appstate.setCurrentApp(sbn.packageName)
            }
        } else if ("android.messaging" in sbn.packageName) {
            if ("메시지" in App.prefs.appListEditText) {
                LED_Matrix_socket.outputStream.write(SMSText.toByteArray())
                Toast.makeText(this, "메시지 LED 반짝!", Toast.LENGTH_LONG).show()
                Appstate.setCurrentApp(sbn.packageName)
            }
        } else if ("com.kakao.talk" in sbn.packageName) {
            if ("카카오톡" in App.prefs.appListEditText) {
                LED_Matrix_socket.outputStream.write(KakaoText.toByteArray())
                Toast.makeText(this, "카카오톡 LED 반짝!", Toast.LENGTH_LONG).show()
                Appstate.setCurrentApp(sbn.packageName)
            }
        }
    }

    override fun onDestroy() {
        Log.i("NotificationListener", "onDestroy()")

        super.onDestroy()

    }

    //앱이 꺼져도 알람을 계속 주는 현상 해결
    override fun onTaskRemoved(rootIntent: Intent) {
        //unregister listeners
        //do any other cleanup if required
        //stop service
        stopSelf()
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

}