package net.iesochoa.gerardodelafuente.sportcityapp.model

//estado de la reserva
data class ReservasUiState(
    val reservas: List<Reserva> = emptyList(),
    val status: RequestStatus = RequestStatus.Idle
)

sealed interface RequestStatus {
    data object Idle : RequestStatus
    data object Loading : RequestStatus
    data object Success : RequestStatus
    data class Error(val message: String) : RequestStatus
}