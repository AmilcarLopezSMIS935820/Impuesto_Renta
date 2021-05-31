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

            if (txtnombre.text.isEmpty() || txtsueldo.text.isEmpty()) {
                Toast.makeText(this, "Debe llenar ambos campos", Toast.LENGTH_SHORT).show()
            } else{
                datos()
            }
        }
    }

    fun datos(){
        val intent = Intent(this, Resultado::class.java)
        val nombre: String = txtnombre.text.toString().trim()
        val sueldo: Double = txtsueldo.text.toString().trim().toDouble()
        val dc = DecimalFormat("$#.00")

        val c1: Double = sueldo * 0.0725
        val c2: Double
        val c3: Double
        val sueldods: Double
        var isr: Double
        var tramo: String
        var sf: Double

        if(sueldo > 999){
            c2 = 30.0
            c3= c1 + c2
            sueldods = sueldo - c3
        } else{
            c2 = sueldo * 0.03
            c3= c1 + c2
            sueldods = sueldo - c3
        }
        val b = Bundle()
        b.putString("nombre", nombre)
        b.putString("afp", dc.format(c1))
        b.putString("isss", dc.format(c2))

        if(sueldods < 472.01){
            isr = 0.0
            tramo = "Tramo I: Sin Retenciones"
            sf = sueldods
            b.putString("isr", dc.format(isr))
            b.putString("tramo", tramo)
            b.putString("sf", dc.format(sf))
        }

        if(sueldods > 472 && sueldods < 895.25){
            isr = ((sueldods - (472)) * 0.10) + 17.67
            tramo = "Se aplico el Tramo II"
            sf = sueldods - (isr)
            b.putString("isr", dc.format(isr))
            b.putString("tramo", tramo)
            b.putString("sf", dc.format(sf))
        }

        if(sueldods > 895.24 && sueldods < 2038.11){
            isr = ((sueldods - (895.24)) * 0.20) + 60
            tramo = "Se aplico el Tramo III"
            sf = sueldods - (isr)
            b.putString("isr", dc.format(isr))
            b.putString("tramo", tramo)
            b.putString("sf", dc.format(sf))
        }

        if(sueldods > 2038.10){
            isr = ((sueldods - (2038.10)) * 0.30) + 288.57
            tramo = "Se aplico el Tramo IV"
            sf = sueldods - (isr)
            b.putString("isr", dc.format(isr))
            b.putString("tramo", tramo)
            b.putString("sf", dc.format(sf))
        }

        intent.putExtras(b)
        startActivity(intent)
        finish()
    }

}

