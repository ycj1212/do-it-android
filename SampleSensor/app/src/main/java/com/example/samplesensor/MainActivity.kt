package com.example.samplesensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var manager: SensorManager
    lateinit var sensors: List<Sensor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textview)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            getSensorList()
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            registerFirstSensor()
        }
    }

    fun getSensorList() {
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensors = manager.getSensorList(Sensor.TYPE_ALL)

        for ((index,sensor) in sensors.withIndex()) {
            println("#$index : ${sensor.name}")
        }
    }

    fun registerFirstSensor() {
        manager.registerListener(object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                var output = "Sensor Timestamp : ${event?.timestamp}\n\n"
                for (i in event!!.values.indices) {
                    output += "Sensor Value #${i+1} : ${event.values[i]}\n"
                }
                println(output)
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        },
        sensors[0],
        SensorManager.SENSOR_DELAY_UI)
    }

    fun println(data: String) {
        textView.append("$data\n")
    }
}
