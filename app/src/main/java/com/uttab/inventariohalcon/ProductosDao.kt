package com.uttab.inventariohalcon

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductosDao {
    @Query("Select * From productos")
    fun getAll():LiveData<List<Producto>>

    @Query("SELECT * FROM productos WHERE idDispositivo = :id")
    fun get(id : Int): LiveData<Producto>

    @Insert
    fun insertAll(vararg productos: Producto)

    @Update
    fun update(producto: Producto)

    @Delete
    fun delete(producto: Producto)
}