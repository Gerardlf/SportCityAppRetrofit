package net.iesochoa.gerardodelafuente.sportcityapp

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import net.iesochoa.gerardodelafuente.sportcityapp.data.local.SportCityDataBase
import net.iesochoa.gerardodelafuente.sportcityapp.data.remote.RetrofitClient
import net.iesochoa.gerardodelafuente.sportcityapp.data.repository.PistasApiRepository
import net.iesochoa.gerardodelafuente.sportcityapp.data.repository.PistasRoomRepository
import net.iesochoa.gerardodelafuente.sportcityapp.data.repository.ReservasApiRepository
import net.iesochoa.gerardodelafuente.sportcityapp.data.repository.ReservasRoomRepository
import net.iesochoa.gerardodelafuente.sportcityapp.model.Pista


//entrada global de la aplicacion(Crea la base de datos de Room)
class SportCityApp : Application() {

    //Base de datos room
    val database by lazy {
        SportCityDataBase.getDatabase(this)
    }

    //repositorio de pistas
    val pistasRoomRepository by lazy {
        PistasRoomRepository(database.pistaDao())
    }

    //repositorio de pistas API
    val pistasApiRepository by lazy {
        PistasApiRepository(RetrofitClient.apiService)
    }


    //repositorio de reservas
    val reservasRoomRepository by lazy {
        ReservasRoomRepository(database.reservaDao())
    }

    //repositorio de reservas API
    val reservasApiRepository by lazy {
        ReservasApiRepository(RetrofitClient.apiService)
    }

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        cargarPistas()
    }

    private fun cargarPistas() {
        applicationScope.launch {
            val existentes = pistasRoomRepository.getAllPistas()

            if (existentes.isEmpty()) {

                val pistasDemo = listOf(
                    Pista(
                        id = 1,
                        nombre = "Pista Central",
                        deporte = "tenis",
                        precioHora = 15.0,
                        descripcion = "Pista central con mayor amplitud y grada cercana. Recomendada para partidos de nivel medio/alto. ideal para partidos entre amigos. Buen estado de superficie y luz suficiente para jugar por la tarde."
                    ),
                    Pista(
                        id = 2,
                        nombre = "Pista Exterior 1",
                        deporte = "tenis",
                        precioHora = 12.0,
                        descripcion = "Pista de tenis de alta calidad, diseñada para ofrecer una experiencia de juego óptima tanto a nivel amateur como profesional. Superficie cuidada, iluminación adecuada y entorno tranquilo para que cada partido se disfrute al máximo."
                    ),
                    Pista(
                        id = 3,
                        nombre = "Pista Exterior 2",
                        deporte = "tenis",
                        precioHora = 12.0,
                        descripcion = "Pista central con mayor amplitud y grada cercana. Recomendada para partidos de nivel medio/alto. Superficie cuidada, iluminación adecuada y entorno tranquilo para que cada partido se disfrute al máximo.",

                        ),
                    Pista(
                        id = 4,
                        nombre = "Pista Exterior 3",
                        deporte = "tenis",
                        precioHora = 10.0,
                        descripcion = "Pista rápida con mayor amplitud y grada cercana. Recomendada para partidos de nivel medio/alto."
                    ),

                    Pista(
                        id = 5,
                        nombre = "Campo F7 Norte",
                        deporte = "Fútbol 7",
                        precioHora = 30.0,
                        descripcion = "Campo de fútbol 7 con césped artificial y buena iluminación nocturna."
                    ),
                    Pista(
                        id = 6,
                        nombre = "Campo F7 Sur",
                        deporte = "Fútbol 7",
                        precioHora = 28.0,
                        descripcion = "Campo perfecto para ligas entre amigos y torneos locales."
                    ),

                    //  padel
                    Pista(
                        id = 7,
                        nombre = "Pista Pádel 1",
                        deporte = "Pádel",
                        precioHora = 18.0,
                        descripcion = "Pista de pádel cubierta, ideal para jugar en cualquier época del año."
                    ),
                    Pista(
                        id = 8,
                        nombre = "Pista Pádel 2",
                        deporte = "Pádel",
                        precioHora = 18.0,
                        descripcion = "Pista de pádel exterior con buena iluminación."
                    ),

                    // BASKETBALL
                    Pista(
                        id = 9,
                        nombre = "Pista Basket Principal",
                        deporte = "Basket",
                        precioHora = 20.0,
                        descripcion = "Pista de baloncesto oficial, perfecta para partidos 5vs5."
                    ),
                    Pista(
                        id = 10,
                        nombre = "Pista Basket Entrenamiento",
                        deporte = "Basket",
                        precioHora = 18.0,
                        descripcion = "Pista para entrenamientos y partidos amistosos."
                    )
                )


                // Inseertto las pistas en room
                pistasRoomRepository.insertPistas(pistasDemo)
            }
        }
    }


}