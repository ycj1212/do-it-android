package com.example.samplenetwork

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var wifiReceiver: WiFiReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            checkConnectivity()
        }

        wifiReceiver = WiFiReceiver()
    }

    fun checkConnectivity() {
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetworkInfo
        if (info != null) {
            if (info.type == ConnectivityManager.TYPE_WIFI) {
                println("WiFi로 설정됨")
            } else if (info.type == ConnectivityManager.TYPE_MOBILE) {
                println("일반망으로 설정됨")
            }

            println("연결 여부: ${info.isConnected}")   // 연결 여부 확인
        } else {
            println("데이터 통신 불가")
        }
    }

    fun println(data: String) {
        textView.append("$data\n")
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(wifiReceiver)
    }

    override fun onResume() {
        super.onResume()

        val filter = IntentFilter()
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        registerReceiver(wifiReceiver, filter)
    }

    inner class WiFiReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            if (action == WifiManager.WIFI_STATE_CHANGED_ACTION) {  // WiFi 상태 확인
                val state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1)
                if (state == WifiManager.WIFI_STATE_ENABLED) {
                    println("WiFi enabled")
                } else if (state == WifiManager.WIFI_STATE_DISABLED) {
                    println("WiFi disabled")
                }
            } else if (action == WifiManager.NETWORK_STATE_CHANGED_ACTION) {
                val info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO) as NetworkInfo
                val manager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val ssid = manager.connectionInfo.ssid

                if (info.state == NetworkInfo.State.CONNECTED) {
                    println("Connected : $ssid")
                } else if (info.state == NetworkInfo.State.DISCONNECTED) {
                    println("Disconnected : $ssid")
                }
            }
        }

    }
}
