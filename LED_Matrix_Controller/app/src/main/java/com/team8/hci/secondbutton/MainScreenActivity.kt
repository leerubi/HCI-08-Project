package com.team8.hci.secondbutton

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothClass
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView

import android.widget.ImageButton
import android.support.v4.app.ActivityCompat.startActivityForResult
import java.util.*
import kotlin.collections.ArrayList
import android.content.ComponentName
import android.content.Context
import android.os.IBinder
import android.content.ServiceConnection
import android.widget.Toast
import org.w3c.dom.Text
import java.util.logging.Logger.global


class MainScreenActivity : AppCompatActivity() {
    lateinit var BtService:BluetoothService
    lateinit var BtSocket:BluetoothSocket
    lateinit var NotificationListenerintent:Intent
    lateinit var ms:NotificationListener // 서비스 객
    var AppState = applicationContext as App
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity","Started!")

        //알람 정보를 받아오는 서비스를 실행합니다.
        NotificationListenerintent = Intent(this,NotificationListener::class.java)
        startService(NotificationListenerintent)

        // 인공지능
        //송중기
        //호랑이
        /*
        AppState.AddKeyword("인공지능" as String)
        AppState.AddKeyword("송중기" as String)
        AppState.AddKeyword("호랑이" as String)
        AppState.AddKeyword("애완" as String) */
        //새로운 Handler를 만듭니다, 블루투스 연결을 서브스레드에서 진행하게 하고, 그 결과를 다시 받아오게 할 수 있습니다.
        val bluetoothconnection = findViewById<TextView>(R.id.bluetoothConnection)
        bluetoothconnection.setOnClickListener {

            Toast.makeText(this, "LED Matrix 탐색을 시도합니다...", Toast.LENGTH_LONG).show()
            if(AppState.getSocket() == null) {
                BtService = BluetoothService(this, this)
                Thread(BtService).start()
            }
            else
                Toast.makeText(this, "LED Matrix가 이미 연결되어 있습니다!", Toast.LENGTH_LONG).show()
            //TODO(연결된 상태에서는 이거 실행하지 않게 해야 합니다)
        }

        val Pattern1button = findViewById<TextView>(R.id.Pattern1)
        Pattern1button.setOnClickListener {
            var appState = applicationContext as App
            var socket = appState.getSocket()
            var s = "1"
            if(socket != null) {
                Log.i("DebugButton","Pattern1Pressed")
                socket.outputStream.write(s.toByteArray())
            }
        }

        val Pattern2button = findViewById<TextView>(R.id.Pattern2)
        Pattern2button.setOnClickListener {
            var appState = applicationContext as App
            var socket = appState.getSocket()
            var s = "2"
            if(socket != null) {
                Log.i("DebugButton","Pattern2Pressed")
                socket.outputStream.write(s.toByteArray())
            }
        }
        //앱 리스트 버튼입니다.
        val applistbutton = findViewById<TextView>(R.id.AppConfig)
        applistbutton.setOnClickListener {
            StartAppListActivity()
        }

    }
    private fun StartAppListActivity()
    {
        val intent = Intent(this, ApplicationListActivity::class.java)
        startActivity(intent)
    }
    fun setBluetoothSocket(soc : BluetoothSocket)
    {
        var appState = applicationContext as App
        appState.setSocket(soc)

        /*
        var BtSocketList = ArrayList<BluetoothSocket>()
        BtSocketList.add(soc)
        var SocketIntent = Intent(this,NotificationListenerintent.javaClass)
        SocketIntent.putExtra("Socket",soc)
        Log.i("Bluetooth_Connect","Socket called")
        bindService(SocketIntent,conn,Context.BIND_AUTO_CREATE)
        */
    }

    override fun onActivityResult(requestCode: Int, resultCode : Int, data: Intent?) {
        var REQUEST_CONNECT_DEVICE = 1
        var REQUEST_ENABLE_BT = 2
        if (requestCode == REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_CANCELED)
                Log.i("Bluetooth_Connection", "Connection Tried but Failed")
            else if (resultCode == Activity.RESULT_OK) {

                if(data != null) {
                    Log.i("Bluetooth_Connection", "Connection Started")
                    BtService.getDeviceInfo(data,this)
                }
                else
                    Log.i("Bluetooth_Connection","Invalid data!")
                // TODO("Validate Connected Bluetooth Device)
            }
        }
    }
}

//블루투스와 관련된 Task를 수행합니다, 별도의 Thread로 분리했습니다.
class BluetoothService(mainAC: Activity,mainCl: MainScreenActivity) : Thread()  {
    var Matrix_Name = "LEDMatrix"
    var is_paired = false
    // TODO(UUID 값을 조율할 필요가 있음)
    val MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    init {
        //메인 액티비티를 받아옵니다.
        start(mainAC,mainCl)
    }
    lateinit var BtAdapter:BluetoothAdapter
    fun start(mainAc: Activity,mainCl: MainScreenActivity) {
        //기본 블루투스 어댑터를 할당합니다
        BtAdapter = BluetoothAdapter.getDefaultAdapter()
        if (BtAdapter != null)
            Log.i("Bluetooth", "Adapter Connected")
        if (getDeviceState()) {
            //블루투스 장치가 있고 꺼져있으면 이를 켜 준다
            if (!BtAdapter.isEnabled) {
                val BluetoothOnRequest = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                mainAc.startActivityForResult(BluetoothOnRequest, 2)
            }
        }
        if(BtAdapter.isEnabled) {
            //페어링 된 기기 중 원하는 기기가 있는지 확인
            var pairedDevices = BtAdapter.bondedDevices
            //페어링 된게 있으면 일단 뒤져보자
            if(pairedDevices.size > 0)
            {
                for(device in pairedDevices)
                {
                    // TODO(검증 방법을 이름에서 MAC 주소로 변경해야 함)
                    //MAC 주소를 사용하는것이 안전하지만 일단은 이름으로
                    if(device.name == Matrix_Name)
                    {
                        Log.i("Bluetooth", "Try to Connect Paired LED Matrix")
                        //본격적인 연결 시작
                        connectDevice(mainCl,device)
                        is_paired = true
                        break
                    }
                }
            }
            //페어링 된 기기 중에는 없네 새로 찾아봐
            if(!is_paired)
            {
                Log.i("Bluetooth", "Try to find New LED Matrix")
                //블루투스 연결 시작(새로운 Activity를 띄운다.)
                var BluetoothIntent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
                mainAc.startActivityForResult(BluetoothIntent, 1)
            }
        }
        else
        {
            Log.i("Bluetooth", "Adapter Access Denied")
        }
    }
    private fun getDeviceState(): Boolean {
        Log.d("Bluetooth", "Check the Bluetooth support")
        if (BtAdapter == null) {
            Log.d("Bluetooth", "Bluetooth is not available")
            return false
        }
        else {
            Log.d("Bluetooth", "Bluetooth is available")
            return true
        }
    }
    private fun connectDevice(mainCl : MainScreenActivity, bluetoothDevice: BluetoothDevice)
    {

        //디바이스 찾았는데 연결 더 해줄 필요 없음
        //BtAdapter.cancelDiscovery()
        //TODO(실제로 연결되었는지 검증해줘야함)
        //페어링된 기기 중 원하는 기기에 대해 연결한 socket을 형성
        var socket = bluetoothDevice.createRfcommSocketToServiceRecord(MY_UUID)
        Log.i("Bluetooth_Connect","ConnectDevice called")
        //나는 한다 연결
        socket.connect()
        sleep(3000)
        mainCl.setBluetoothSocket(socket)
    }

    public fun getDeviceInfo(data: Intent,mainCl: MainScreenActivity)
    {
        val device = data.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
        connectDevice(mainCl,device)
    }

}

