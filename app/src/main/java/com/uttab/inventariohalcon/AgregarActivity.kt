package com.uttab.inventariohalcon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_productos.*
import kotlinx.android.synthetic.main.agregar_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgregarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agregar_activity)

        var idDispositivo : Int? = null

        if(intent.hasExtra("producto")){
            val producto = intent.extras?.getSerializable("producto") as Producto

            txnombre.setText(producto.nombre)
            txprecio.setText(producto.precio.toString())
            txcantidad.setText(producto.cantidad.toString())
            idDispositivo = producto.idDispositivo
        }



        val database = AppDatabase.getDatabase(this)

        btnguardar.setOnClickListener {
            val nombre = txnombre.text.toString()
            val precio = txprecio.text.toString().toDouble()
            val cantidad = txcantidad.text.toString().toInt()

            val producto = Producto(nombre, precio, cantidad, R.drawable.radio)

            if (idDispositivo != null){
                CoroutineScope(Dispatchers.IO).launch {
                    producto.idDispositivo = idDispositivo
                    database.productos().update(producto)
                    this@AgregarActivity.finish()

                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    database.productos().insertAll(producto)

                    this@AgregarActivity.finish()
                }
            }


        }

    }
}