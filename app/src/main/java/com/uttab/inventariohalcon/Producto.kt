package com.uttab.inventariohalcon

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "productos")
class Producto (
    val nombre: String,
    val precio: Double,
    val cantidad: Int,
    val imagen: Int,
    @PrimaryKey(autoGenerate = true)
    var idDispositivo : Int = 0
) : Serializable
