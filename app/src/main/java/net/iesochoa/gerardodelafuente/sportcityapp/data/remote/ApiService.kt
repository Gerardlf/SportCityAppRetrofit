package net.iesochoa.gerardodelafuente.sportcityapp.data.remote

import net.iesochoa.gerardodelafuente.sportcityapp.model.Pista
import net.iesochoa.gerardodelafuente.sportcityapp.model.Reserva
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("api/pistas")
    suspend fun getPistas(): List<Pista>

    @GET("api/reservas")
    suspend fun getReservas(): List<Reserva>

    @GET("api/reservas/usuario/{usuarioId}")
    suspend fun getReservasPorUsuario(
        @Path("usuarioId") usuarioId: String
    ): List<Reserva>


    @POST("api/reservas")
    suspend fun crearReserva(@Body reserva: Reserva): Reserva

    @DELETE("api/reservas/{id}")
    suspend fun borrarReserva(@Path("id") id: Int)


}