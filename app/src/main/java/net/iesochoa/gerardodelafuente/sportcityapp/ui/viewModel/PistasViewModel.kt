package net.iesochoa.gerardodelafuente.sportcityapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.iesochoa.gerardodelafuente.sportcityapp.SportCityApp
import net.iesochoa.gerardodelafuente.sportcityapp.data.repository.PistasApiRepository
import net.iesochoa.gerardodelafuente.sportcityapp.model.PistasUistate


//View model para las pistas
class PistasViewModel(
    application: Application
) : AndroidViewModel(application) {

    //repo room
    private val pistasRepository: PistasApiRepository =
        (application as SportCityApp).pistasApiRepository


    private val _uiState = MutableStateFlow(PistasUistate())
    val uiState: StateFlow<PistasUistate> = _uiState.asStateFlow()

    //funcion generica para cargas lso pistas
    fun cargarPistas(deporte: String) {

        viewModelScope.launch {
            //cargando
            _uiState.update { estado ->
                estado.copy(
                    isLoading = true,
                    errorMessage = (null)
                )
            }
            try {
                //obtengo pistas por deporte
                val pistasRepositorio = pistasRepository.getPistasByDeporte(deporte)

                _uiState.update { estado ->
                    estado.copy(
                        isLoading = false,
                        pistas = pistasRepositorio
                    )
                }
            } catch (ex: Exception) {

                _uiState.update { estado ->
                    estado.copy(
                        isLoading = false,
                        errorMessage = "Error al cargar las pistas de $deporte"
                    )
                }
            }
        }
    }


    //funcion para cargar pistas
    private fun cargarPistasTenis() {
        viewModelScope.launch {

            //cargando
            _uiState.update { estadoActual ->
                estadoActual.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }
            try {
                //Obtengo las pistas de tenis del repositorio
                val pistasRepo = pistasRepository.getPistasByDeporte("tenis")

                _uiState.update { estadoActual ->
                    estadoActual.copy(
                        isLoading = false,
                        pistas = pistasRepo
                    )
                }
            } catch (excepcion: Exception) {

                //si hay error
                _uiState.update { estadoActual ->
                    estadoActual.copy(
                        isLoading = false,
                        errorMessage = "Error al cargar las pistas"
                    )
                }
            }
        }
    }

}