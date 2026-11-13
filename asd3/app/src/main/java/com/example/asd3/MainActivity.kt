package com.example.asd3

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var txtAcelerometro: TextView
    private lateinit var txtGiroscopio: TextView
    private lateinit var txtLuz: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtAcelerometro = findViewById(R.id.acelerometroBtn)
        txtGiroscopio = findViewById(R.id.giroscopioBtn)
        txtLuz = findViewById(R.id.luzBtn)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val lista = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (s in lista) {
            Log.d("SENSORES", s.name)
        }
    }

    override fun onResume() {
        super.onResume()
        val tipos = listOf(
            Sensor.TYPE_ACCELEROMETER,
            Sensor.TYPE_MAGNETIC_FIELD,
            Sensor.TYPE_LIGHT
        )
        for (tipo in tipos) {
            val sensor = sensorManager.getDefaultSensor(tipo)
            if (sensor != null) {
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> txtAcelerometro.text =
                "Acelerómetro\nX: ${event.values[0]}\nY: ${event.values[1]}\nZ: ${event.values[2]}"

            Sensor.TYPE_MAGNETIC_FIELD -> txtGiroscopio.text =
                "Magnetómetro\nX: ${event.values[0]}\nY: ${event.values[1]}\nZ: ${event.values[2]} µT"

            Sensor.TYPE_LIGHT -> txtLuz.text =
                "Luz: ${event.values[0]} lx"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
