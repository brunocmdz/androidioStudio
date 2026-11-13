 package com.example.asd2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
        //actividad4
        val Iemail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val Ipassword = findViewById<EditText>(R.id.editTextTextPassword)
        val result = findViewById<TextView>(R.id.result)
        val login = findViewById<Button>(R.id.Login)

        var contador = 0

        var constanteEmail = "test@gmail.com"
        var constantePassword = "123abc"

        login.setOnClickListener {

            var email = Iemail.text.toString()
            var password = Ipassword.text.toString()

            if(email == constanteEmail && password == constantePassword){
                result.text = "Login correcto"
            } else {
                result.text = "Incorrecto ${contador}"
                contador += 1
                val intent = Intent(this, error::class.java)
                startActivity(intent)

                if (contador > 3){
                    Iemail.isEnabled = false
                    Ipassword.isEnabled = false
                    login.isEnabled = false
                    result.text = "bloqueado, Espera 10 segundos capo"

                    Handler(Looper.getMainLooper()).postDelayed({
                        Iemail.isEnabled = true
                        Ipassword.isEnabled = true
                        login.isEnabled = true
                        contador = 0
                        result.text = "inetnta denuvo"
                    }, 10000)
                }
            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}