package net.iesochoa.gerardodelafuente.sportcityapp.ui.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.iesochoa.gerardodelafuente.sportcityapp.SportCityApp
import net.iesochoa.gerardodelafuente.sportcityapp.data.FakeReservasRepository
import net.iesochoa.gerardodelafuente.sportcityapp.data.repository.ReservasApiRepository
import net.iesochoa.gerardodelafuente.sportcityapp.data.repository.ReservasRoomRepository
import net.iesochoa.gerardodelafuente.sportcityapp.model.Reserva
import net.iesochoa.gerardodelafuente.sportcityapp.model.ReservasUiState

//View model de las reservas
class ReservasViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val reservasRepository: ReservasRoomRepository =
        (application as SportCityApp).reservasRoomRepository

    private val reservasApiRepository: ReservasApiRepository =
        (application as SportCityApp).reservasApiRepository

    private val _uiState = MutableStateFlow(ReservasUiState())
    val uiState: StateFlow<ReservasUiState> = _uiState.asStateFlow()


    init {
        cargarReservas()
    }

    //Cargar reservas desde la api
    fun cargarReservas() {
        viewModelScope.launch {
            val reservasApi = reservasApiRepository.getAllReservas()
            _uiState.update { actual ->
                actual.copy(
                    reservas = reservasApi
                )
            }
        }
    }

    //Crear una reserva nueva
    fun crearReserva(
        pistaId: Int,
        pistaNombre: String,
        fecha: String,
        hora: String,
        nombreCliente: String,
        telefonoCliente: String,
        comentario: String?,
        deporte: String
    ) {
        val reserva = Reserva(
            id = null,
            pistaId = pistaId,
            pistaNombre = pistaNombre,
            fecha = fecha,
            hora = hora,
            nombreCliente = nombreCliente,
            telefonoCliente = telefonoCliente,
            comentario = comentario,
            deporte = deporte
        )

        //añado reserva
        viewModelScope.launch {
            reservasApiRepository.addReserva(reserva)
        }
    }

    fun borrarReserva(reserva: Reserva) {
        viewModelScope.launch {
            reservasRepository.deleteReserva(reserva)
        }

    }
}