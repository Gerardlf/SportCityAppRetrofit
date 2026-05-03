package net.iesochoa.gerardodelafuente.sportcityapp.ui.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.iesochoa.gerardodelafuente.sportcityapp.SportCityApp
import net.iesochoa.gerardodelafuente.sportcityapp.data.FakeReservasRepository
import net.iesochoa.gerardodelafuente.sportcityapp.data.repository.ReservasApiRepository
import net.iesochoa.gerardodelafuente.sportcityapp.data.repository.ReservasRoomRepository
import net.iesochoa.gerardodelafuente.sportcityapp.model.RequestStatus
import net.iesochoa.gerardodelafuente.sportcityapp.model.Reserva
import net.iesochoa.gerardodelafuente.sportcityapp.model.ReservasUiState
import java.lang.Exception

//View model de las reservas
class ReservasViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val reservasRepository: ReservasRoomRepository =
        (application as SportCityApp).reservasRoomRepository

    private val reservasApiRepository: ReservasApiRepository =
        (application as SportCityApp).reservasApiRepository

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _uiState = MutableStateFlow(ReservasUiState())
    val uiState: StateFlow<ReservasUiState> = _uiState.asStateFlow()


    init {
        cargarReservas()
    }

    //Cargar reservas desde la api del usuario que ha iniciado sesion
    fun cargarReservas() {

        val usuarioId = auth.currentUser?.uid

        if (usuarioId == null) {
            _uiState.update { actual ->
                actual.copy(
                    reservas = emptyList(),
                    status = RequestStatus.Error("No hay usuario iniciado")
                )
            }
            return
        }
        viewModelScope.launch {

            _uiState.update { actual ->
                actual.copy(
                    status = RequestStatus.Loading
                )
            }
            try {
                val reservasApi = reservasApiRepository.getReservasPorUsuario(usuarioId)

                _uiState.update { actual ->
                    actual.copy(
                        reservas = reservasApi,
                        status = RequestStatus.Success
                    )
                }
            } catch (e: Exception) {
                _uiState.update { actual ->
                    actual.copy(
                        status = RequestStatus.Error("No se pudieron cargar las reservas. Render puede estar iniciandose...")
                    )
                }
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
        deporte: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        //obtengo el usuario de firebase
        val usuarioId = auth.currentUser?.uid

        if (usuarioId == null) {
            onError("No Hay Usuario Iniciado")
            return
        }


        val reserva = Reserva(
            id = null,
            pistaId = pistaId,
            pistaNombre = pistaNombre,
            fecha = fecha,
            hora = hora,
            nombreCliente = nombreCliente,
            telefonoCliente = telefonoCliente,
            comentario = comentario,
            deporte = deporte,
            usuarioId = usuarioId
        )
        //añado reserva
        viewModelScope.launch {
            try {
                reservasApiRepository.addReserva(reserva)
                onSuccess()

            } catch (e: retrofit2.HttpException) {

                if (e.code() == 409){
                    onError("Esta pista ya está reservada para esa fecha y hora. Prueba con otra hora.")
                }
                else{
                    onError("No se pudo crear la reserva. Inténtalo de nuevo.")
                }
            }catch (e: Exception){
                onError("No se pudo conectar con la API. Render puede estar iniciándose.")
            }
        }
    }

    fun borrarReserva(reserva: Reserva) {
        val id = reserva.id ?: return

        viewModelScope.launch {
            reservasApiRepository.deleteReserva(id)
            cargarReservas()
        }

    }
}