package com.example.asd3

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var txtAcelerometro: TextView
    private lateinit var txtMagnetometro: TextView
    private lateinit var txtLuz: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtAcelerometro = findViewById(R.id.acelerometroBtn)
        txtMagnetometro = findViewById(R.id.magnetrometoBtns)
        txtLuz = findViewById(R.id.luzBtn)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()

        val acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (acelerometro != null) {
            sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL)
        }

        val magnetometro = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        if (magnetometro != null) {
            sensorManager.registerListener(this, magnetometro, SensorManager.SENSOR_DELAY_NORMAL)
        }

        val luz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        if (luz != null) {
            sensorManager.registerListener(this, luz, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        val tipo = event.sensor.type

        if (tipo == Sensor.TYPE_ACCELEROMETER) {
            txtAcelerometro.text = "Acelerómetro\nX: ${event.values[0]}\nY: ${event.values[1]}\nZ: ${event.values[2]}"
        }

        if (tipo == Sensor.TYPE_MAGNETIC_FIELD) {
            txtMagnetometro.text = "Magnetómetro\nX: ${event.values[0]}\nY: ${event.values[1]}\nZ: ${event.values[2]} µT"
        }

        if (tipo == Sensor.TYPE_LIGHT) {
            txtLuz.text = "Luz: ${event.values[0]} lx"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}