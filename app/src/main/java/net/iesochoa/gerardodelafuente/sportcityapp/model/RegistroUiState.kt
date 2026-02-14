package net.iesochoa.gerardodelafuente.sportcityapp.model


//estado del registro

data class RegistroUiState (
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isRegistrado: Boolean = false
)