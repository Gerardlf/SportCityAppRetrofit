package net.iesochoa.gerardodelafuente.sportcityapp.ui.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iesochoa.gerardodelafuente.sportcityapp.data.auth.AuthRepository
import net.iesochoa.gerardodelafuente.sportcityapp.model.RegistroUiState
import net.iesochoa.gerardodelafuente.sportcityapp.ui.screens.RegistroScreen

class RegistroViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistroUiState())
    val uiState: StateFlow<RegistroUiState> = _uiState.asStateFlow()

    //cuando pulsamos el botob crear cuenta
    fun onRegisterClicked(email: String, password: String) {

        //Cargando..., sin error y aun no registrado(false)

        _uiState.update { actual ->
            actual.copy(
                isLoading = true,
                errorMessage = null,
                isRegistrado = false
            )
        }

        //llamo al repo de firebase

        authRepository.registro(
            email = email,
            password = password
        ) { isSuccess, errorMessage ->
            if (isSuccess) {
                //registro correcto
                _uiState.update { actual ->
                    actual.copy(
                        isLoading = false,
                        errorMessage = null,
                        isRegistrado = true
                    )
                }
            } else {
                //EError en firebase
                _uiState.update { actual ->
                    actual.copy(
                        isLoading = false,
                        errorMessage = errorMessage ?: "Error al crear la cuenta",
                        isRegistrado = false
                    )
                }
            }
        }
    }
}