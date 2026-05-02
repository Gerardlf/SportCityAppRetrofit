package net.iesochoa.gerardodelafuente.sportcityapp.data.repository

import net.iesochoa.gerardodelafuente.sportcityapp.data.remote.ApiService
import net.iesochoa.gerardodelafuente.sportcityapp.model.Reserva

class ReservasApiRepository(
    private val apiService: ApiService
) {
    suspend fun getAllReservas(): List<Reserva> {
        return apiService.getReservas()
    }

    suspend fun addReserva(reserva: Reserva): Reserva {
        return apiService.crearReserva(reserva)
    }

    suspend fun getReservasPorUsuario(usuarioId: String): List<Reserva> {
        return apiService.getReservasPorUsuario(usuarioId)
    }

    suspend fun deleteReserva(id: Int) {
        apiService.borrarReserva(id)
    }
}