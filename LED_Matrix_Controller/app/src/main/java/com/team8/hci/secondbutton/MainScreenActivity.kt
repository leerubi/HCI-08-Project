package com.team8.hci.secondbutton

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothClass
import android.bluetooth.BluetoothDevice
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


class MainScreenActivity : AppCompatActivity() {
    lateinit var BtService:BluetoothService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity","Started!")
        //알람 정보를 받아오는 서비스를 실행합니다.
        val NotificationListenerintent = Intent(this,NotificationListener::class.java)
        startService(NotificationListenerintent)

        //새로운 Handler를 만듭니다, 블루투스 연결을 서브스레드에서 진행하게 하고, 그 결과를 다시 받아오게 할 수 있습니다.
        val bluetoothconnection = findViewById<TextView>(R.id.bluetoothConnection)
        bluetoothconnection.setOnClickListener {
            BtService = BluetoothService(this)
            Thread(BtService).start()
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

    override fun onActivityResult(requestCode: Int, resultCode : Int, data: Intent?) {
        var REQUEST_CONNECT_DEVICE = 1
        var REQUEST_ENABLE_BT = 2
        if (requestCode == REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_CANCELED)
                Log.i("Bluetooth_Connection", "Connection Tried but Failed")
            else if (resultCode == Activity.RESULT_OK) {

                if(data != null) {
                    Log.i("Bluetooth_Connection", "Connection Started")
                    BtService.getDeviceInfo(data)
                }
                else
                    Log.i("Bluetooth_Connection","Invalid data!")
                // TODO("Validate Connected Bluetooth Device)
            }
        }
    }
}

//블루투스와 관련된 Task를 수행합니다, 별도의 Thread로 분리했습니다.
class BluetoothService(mainAC: Activity) : Thread()  {
    var Matrix_Name = "LEDMatrix"
    var is_paired = false
    // TODO(UUID 값을 조율할 필요가 있음)
    val MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    init {
        //메인 액티비티를 받아옵니다.
        start(mainAC)
    }
    lateinit var BtAdapter:BluetoothAdapter
    fun start(mainAc: Activity) {
        //기본 블루투스 어댑터를 할당합니다.
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
                        //본격적인 연결 시작
                        connectDevice(device)
                        is_paired = true
                        break
                    }
                }
            }
            //페어링 된 기기 중에는 없네 새로 찾아봐
            else if(!is_paired)
            {
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
    private fun connectDevice(bluetoothDevice: BluetoothDevice)
    {
        Log.i("Bluetooth_Connect","ConnectDevice called");
        //디바이스 찾았는데 연결 더 해줄 필요 없음
        BtAdapter.cancelDiscovery()
        //페어링된 기기 중 원하는 기기에 대해 연결한 socket을 형성
        var socket = bluetoothDevice.createRfcommSocketToServiceRecord(MY_UUID)
        //나는 한다 연결
        socket.connect()
        var outputStream=socket.getOutputStream()
        var inputStream=socket.getInputStream()
        var s = "A"
        //정보 보내기!
        outputStream.write(s.toByteArray())
    }

    public fun getDeviceInfo(data: Intent)
    {
        val device = data.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
        connectDevice(device)
    }

}
