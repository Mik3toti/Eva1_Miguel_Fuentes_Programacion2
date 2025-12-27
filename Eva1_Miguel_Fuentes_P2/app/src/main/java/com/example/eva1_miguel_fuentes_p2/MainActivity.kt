package com.example.eva1_miguel_fuentes_p2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.eva1_miguel_fuentes_p2.modelo.CuentaMesa
import com.example.eva1_miguel_fuentes_p2.modelo.ItemMenu
import com.example.eva1_miguel_fuentes_p2.modelo.ItemMesa
import java.text.NumberFormat
import java.time.temporal.TemporalAmount
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val pastelDeChoclo = ItemMenu ("Pastel de choclo", 12000)
        val cazuela = ItemMenu("Cazuela" , 10000);

        val itempastelDeChoclo = ItemMesa(0,pastelDeChoclo)
        val itemCazuela = ItemMesa (0,cazuela)


        val totalMesa = CuentaMesa(1)
        totalMesa.agregarItem(itempastelDeChoclo)
        totalMesa.agregarItem(itemCazuela)

        val Total= findViewById<TextView>(R.id.Total)
        val TotalComida1 = findViewById<TextView>(R.id.TotalComida1)
        val TotalComida2 = findViewById<TextView>(R.id.TotalComida2)
        val CantidadComida1 = findViewById<EditText>(R.id.NumeroComida1)
        val CantidadComida2 = findViewById<EditText>(R.id.NumeroComida2)
        val TotalComida = findViewById<TextView>(R.id.ComidaTotal)
        val propina = findViewById<TextView>(R.id.Propina)
        val switchPropina = findViewById<Switch>(R.id.switchPropina)

        val formatoCLP = NumberFormat.getCurrencyInstance(Locale("es","CL"))

        fun actualizarPantalla(){
            TotalComida1.setText(formatoCLP.format(itempastelDeChoclo.calcularSubtotal()))
            TotalComida2.setText(formatoCLP.format(itemCazuela.calcularSubtotal()))
            TotalComida.setText(formatoCLP.format(totalMesa.calcularTotalSinPropina()))
            propina.setText(formatoCLP.format(totalMesa.calcularPropina()))
            Total.setText(formatoCLP.format(totalMesa.calcularTotalConPropina()))

        }

        CantidadComida1.setText("0")
        CantidadComida2.setText("0")

        switchPropina.isChecked=false
        totalMesa.aceptaPropina =false
        actualizarPantalla()


        actualizarPantalla()
        CantidadComida1.addTextChangedListener {
            itempastelDeChoclo.cantidad = it.toString().toIntOrNull() ?:0
            actualizarPantalla()
        }

        CantidadComida2.addTextChangedListener{
            itemCazuela.cantidad = it.toString().toIntOrNull() ?:0
            actualizarPantalla()
        }

        switchPropina.setOnCheckedChangeListener { _, isChecked -> totalMesa.aceptaPropina=isChecked
        actualizarPantalla()}
        //override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            //}

            //override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            //}

            //override fun afterTextChanged(s: Editable?) {

              //  TotalComida1.setText("aqui va el total " + s)
            //}





    }
}