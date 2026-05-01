package net.iesochoa.gerardodelafuente.sportcityapp.data.repository

import net.iesochoa.gerardodelafuente.sportcityapp.data.remote.ApiService
import net.iesochoa.gerardodelafuente.sportcityapp.model.Pista

class PistasApiRepository(
    private val apiService: ApiService
) {
    suspend fun getAllPistas(): List<Pista> {
        return apiService.getPistas()
    }

    suspend fun getPistasByDeporte(deporte: String): List<Pista> {
        val pistas = apiService.getPistas()

        return pistas.filter { pista ->
            pista.deporte.equals(deporte, ignoreCase = true)
        }
    }
}