package com.smis935820.impuesto_renta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    var v= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btncalcular.setOnClickListener {
           datos()
        }
    }

    fun datos(){
        val intent = Intent(this, Resultado::class.java)
        val nombre: String = txtnombre.text.toString().trim()
        val sueldo: Double = txtsueldo.text.toString().trim().toDouble()
        val dc = DecimalFormat("$#.00")

        val c1 = sueldo * 0.0725
        val c2 = sueldo * 0.03
        val c3: Double = c1 + c2
        var isr: Double
        var tramo: String
        var sf: Double

        val b = Bundle()
        b.putString("nombre", nombre)
        b.putString("afp", dc.format(c1))
        b.putString("isss", dc.format(c2))

        if(sueldo < 472.01){
            isr = 0.0
            tramo = "Tramo I: Sin Retenciones"
            sf = sueldo - c3
            b.putString("isr", dc.format(isr))
            b.putString("tramo", tramo)
            b.putString("sf", dc.format(sf))
        }

        if(sueldo > 472 && sueldo < 895.25){
            isr = ((sueldo - (c3 + 472)) * 0.10) + 17.67
            tramo = "Tramo II: Se aplican Retenciones"
            sf = sueldo - (isr+c3)
            b.putString("isr", dc.format(isr))
            b.putString("tramo", tramo)
            b.putString("sf", dc.format(sf))
        }

        if(sueldo > 895.24 && sueldo < 2038.11){
            isr = ((sueldo - (c3 + 895.24)) * 0.20) + 60
            tramo = "Tramo III: Se aplican Retenciones"
            sf = sueldo - (isr+c3)
            b.putString("isr", dc.format(isr))
            b.putString("tramo", tramo)
            b.putString("sf", dc.format(sf))
        }

        if(sueldo > 2038.10){
            isr = ((sueldo - (c3 + 2038.10)) * 0.30) + 288.57
            tramo = "Tramo IV: Se aplican Retenciones"
            sf = sueldo - (isr+c3)
            b.putString("isr", dc.format(isr))
            b.putString("tramo", tramo)
            b.putString("sf", dc.format(sf))
        }


        intent.putExtras(b)
        startActivity(intent)
        finish()
    }

}

