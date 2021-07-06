package com.uttab.inventariohalcon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radios.setOnClickListener {
            val intent: Intent = Intent (this, RadioActivity::class.java)
            startActivity(intent)
        }

        btnguardar.setOnClickListener {
            val intent: Intent = Intent (this, AgregarActivity::class.java)
            startActivity(intent)
        }
    }
}