package net.iesochoa.gerardodelafuente.sportcityapp.ui.navigation

import android.net.Uri

//clase para la navegacion (sellada) cada pantalla de la aplicacion

sealed class ScreenNavigation(val route: String) {

    //pantalla login
    object Login : ScreenNavigation("login")



    //pantalla inicio(con todos los depostes de sportcity)
    object Home : ScreenNavigation("home")



    //pantalla de listado de pistas de tenis
    object PistasDeporte : ScreenNavigation(
        "pistas_deporte/{deporte}"
    ) {
        fun createRoute(deporte: String): String =
            "pistas_deporte/${Uri.encode(deporte)}"
    }



    //pantalla detalle de pista
    object DetallePista : ScreenNavigation("detalle_pista/{pistaId}/{nombrePista}") {
        fun crearRuta(pistaId: Int, nombrePista: String) =
            "detalle_pista/$pistaId/${Uri.encode(nombrePista)}"
    }



    object ReservaForm :
        ScreenNavigation("reserva_formulario/{pistaId}/{hora}/{nombrePista}/{fecha}/{deporte}") {
        fun createRoute(pistaId: Int, hora: String, nombrePista: String, fecha: String, deporte: String) =
            "reserva_formulario/$pistaId/$hora/${Uri.encode(nombrePista)}/${Uri.encode(fecha)}/${Uri.encode(deporte)}"
    }



    object MisReservas : ScreenNavigation("mis_reservas")



    object ConfirmacionReserva :
        ScreenNavigation("confirmacion_reserva/{deporte}/{pistaNombre}/{hora}") {
        fun crearRuta(deporte: String, pistaNombre: String, hora: String): String {
            return "confirmacion_reserva/" +
                    "${Uri.encode(deporte)}/" +
                    "${Uri.encode(pistaNombre)}/" +
                    "${Uri.encode(hora)}"
        }
    }


    //Pantalla registro
    object Register: ScreenNavigation("register")


}