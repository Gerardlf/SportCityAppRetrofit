package net.iesochoa.gerardodelafuente.sportcityapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

//entidad reservas
@Entity(tableName= "reservas")
data class ReservaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pistaId:Int,
    val pistaNombre:String,
    val fecha: String,
    val hora: String,
    val nombreCliente: String,
    val telefonoCliente: String,
    val comentario: String?= null,
    val deporte: String,


)