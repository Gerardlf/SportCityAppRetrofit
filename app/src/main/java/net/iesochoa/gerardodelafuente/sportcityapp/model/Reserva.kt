package net.iesochoa.gerardodelafuente.sportcityapp.model

//data class de reserva
data class Reserva(

    val id: Int? = null,
    val pistaId: Int,
    val pistaNombre: String,
    val fecha: String,
    val hora: String,
    val nombreCliente: String,
    val telefonoCliente: String,
    val comentario: String? = null,
    val deporte: String,
)