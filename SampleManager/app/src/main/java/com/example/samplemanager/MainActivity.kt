package com.example.samplemanager

import android.app.ActivityManager
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            Toast.makeText(this, "button clicked!", Toast.LENGTH_SHORT).show()
            getServiceList()
        }

        val button2: Button = findViewById(R.id.button2)
        button.setOnClickListener {
            Toast.makeText(this, "button clicked!", Toast.LENGTH_SHORT).show()
            getCurrentActivity()
        }

        val button3: Button = findViewById(R.id.button3)
        button.setOnClickListener {
            Toast.makeText(this, "button clicked!", Toast.LENGTH_SHORT).show()
            getAppList()
        }

        val button4: Button = findViewById(R.id.button4)
        button.setOnClickListener {
            Toast.makeText(this, "button clicked!", Toast.LENGTH_SHORT).show()
            findActivity()
        }

        val button5: Button = findViewById(R.id.button5)
        button.setOnClickListener {
            Toast.makeText(this, "button clicked!", Toast.LENGTH_SHORT).show()
            setAlarm()
        }
    }

    fun getServiceList() {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processInfoList = manager.runningAppProcesses   // 실행 중인 프로세스 확인

        for (i in processInfoList.indices) {
            val info: ActivityManager.RunningAppProcessInfo = processInfoList[i]
            println("#$i -> ${info.pid}, ${info.processName}")
        }
    }

    fun getCurrentActivity() {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val taskList = manager.getRunningTasks(1)

        val info = taskList[0]
        println("Running Task -> ${info.topActivity.toString()}")
    }

    fun getAppList() {
        val manager = packageManager
        val appInfoList = manager.getInstalledApplications(PackageManager.GET_META_DATA)

        for (i in appInfoList.indices) {
            val info = appInfoList[i]
            println("#$i -> ${info.loadLabel(manager).toString()}, ${info.packageName}")
        }
    }

    fun findActivity() {
        val manager = packageManager

        val intent = Intent(this, MainActivity::class.java)
        val activityInfoList = manager.queryIntentActivities(intent, 0)

        for (i in activityInfoList.indices) {
            val info = activityInfoList[i]
            println("#$i -> ${info.activityInfo.applicationInfo.packageName}")
        }
    }

    fun setAlarm() {
        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        manager.set(AlarmManager.RTC, System.currentTimeMillis()+60000, pendingIntent)
    }

    fun println(data: String) {
        textView.append("$data\n")
    }
}
