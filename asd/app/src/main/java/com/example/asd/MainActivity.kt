package com.example.asd

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            textView.text = "hola"
        }
        val num1 = findViewById<EditText>(R.id.inputA)
        val num2 = findViewById<EditText>(R.id.inputB)
        val resultado = findViewById<TextView>(R.id.resultado)
        val sumar = findViewById<Button>(R.id.sumar)

        sumar.setOnClickListener {
            var a = num1.text.toString().toIntOrNull()
            var b = num2.text.toString().toIntOrNull()
            if (a == null){
                 a = 0
            }
            if (b== null){
             b = 0
            }
            resultado.text = "Resultado: ${a + b}"
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}