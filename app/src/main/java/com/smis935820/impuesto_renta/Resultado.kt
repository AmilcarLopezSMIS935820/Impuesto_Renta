package com.smis935820.impuesto_renta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_resultado.*
import kotlin.time.seconds

class Resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val bundle = intent.extras
        tvnombre.text = "Bienvenido  " + bundle?.getString("nombre")
        afp.text = "AFP: " + bundle?.getString("afp")
        isss.text = "ISSS: " + bundle?.getString("isss")
        ISR.text = "ISR: " + bundle?.getString("isr")
        tramo.text = bundle?.getString("tramo")
        sueldoF.text = "Sueldo Final: " + bundle?.getString("sf")
        
    }

    fun regresar(view : View){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}


