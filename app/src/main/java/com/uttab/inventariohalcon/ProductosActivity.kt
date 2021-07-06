package com.uttab.inventariohalcon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_productos.*
import kotlinx.android.synthetic.main.agregar_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductosActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var  producto: Producto
    private lateinit var  productoLiveData: LiveData<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        database = AppDatabase.getDatabase(this)

        val idDispositivo = intent.getIntExtra("id", 0)

        productoLiveData = database.productos().get(idDispositivo)

        productoLiveData.observe(this, Observer {
            producto = it

            nombreProduc.text = producto.nombre
            precioproduc.text = "$${producto.precio}"
            cantidadproduc.text = "Cantidad: ${producto.cantidad}"
            imageView2.setImageResource(R.drawable.radio )
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.producto_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.edit_item ->{
                val intent = Intent(this, AgregarActivity::class.java)
                intent.putExtra("producto", producto)
                startActivity(intent)
            }

            R.id.eliminar_item->{
                productoLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.productos().delete(producto)
                    this@ProductosActivity.finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}