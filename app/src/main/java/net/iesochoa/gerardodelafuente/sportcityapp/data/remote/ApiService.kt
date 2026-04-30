package net.iesochoa.gerardodelafuente.sportcityapp.data.remote

import net.iesochoa.gerardodelafuente.sportcityapp.model.Pista
import net.iesochoa.gerardodelafuente.sportcityapp.model.Reserva
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("api/pistas")
    suspend fun getPistas(): List<Pista>

    @GET("api/reservas")
    suspend fun getReservas(): List<Reserva>


    @POST("api/reservas")
    suspend fun crearReserva(@Body reserva: Reserva): Reserva

}