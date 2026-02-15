package net.iesochoa.gerardodelafuente.sportcityapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import net.iesochoa.gerardodelafuente.sportcityapp.ui.screens.AyudaScreen
import net.iesochoa.gerardodelafuente.sportcityapp.ui.screens.ConfirmacionReservaScreen
import net.iesochoa.gerardodelafuente.sportcityapp.ui.screens.DetallePistaScreen
import net.iesochoa.gerardodelafuente.sportcityapp.ui.screens.HomeScreen
import net.iesochoa.gerardodelafuente.sportcityapp.ui.screens.MisReservasScreen
import net.iesochoa.gerardodelafuente.sportcityapp.ui.screens.PerfilScreen
import net.iesochoa.gerardodelafuente.sportcityapp.ui.screens.PistasDeporteScreen
import net.iesochoa.gerardodelafuente.sportcityapp.ui.screens.RegistroScreen
import net.iesochoa.gerardodelafuente.sportcityapp.ui.screens.ReservaFormScreen
import net.iesochoa.gerardodelafuente.sportcityapp.ui.screens.loginScreen
import net.iesochoa.gerardodelafuente.sportcityapp.ui.viewModel.PistasViewModel
//Composables de navegacion Navhost
@Composable
fun SportCityNavHost(
    navController: NavHostController
) {
    val pistasViewModel: PistasViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = ScreenNavigation.Login.route
    ) {
        //pantalla de login
        composable(route = ScreenNavigation.Login.route) {
            loginScreen(navController = navController)
        }

        //Pantalla home
        composable(route = ScreenNavigation.Home.route) {
            HomeScreen(navController = navController)
        }

        //pantalla pistas dde deporte general
        composable(
            route = ScreenNavigation.PistasDeporte.route,
            arguments = listOf(
                navArgument("deporte") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val deporte = backStackEntry.arguments?.getString("deporte") ?: "tenis"

            PistasDeporteScreen(
                navController= navController,
                deporte = deporte,
                viewModel = pistasViewModel
            )

        }


        //pantalla detallePista
        composable(
            route = ScreenNavigation.DetallePista.route, arguments = listOf(
                navArgument("pistaId") { type = NavType.IntType },
                navArgument("nombrePista") { type = NavType.StringType }

            )) { backStackEntry ->
            val pistaId = backStackEntry.arguments?.getInt("pistaId") ?: -1
            val nombrePista = backStackEntry.arguments?.getString("nombrePista") ?: ""

            DetallePistaScreen(
                navController = navController,
                pistaId = pistaId,
                nombrePista = nombrePista,
                viewModel = pistasViewModel
            )

        }
        //formulario
        composable(
            route = ScreenNavigation.ReservaForm.route,
            arguments = listOf(
                navArgument("pistaId") { type = NavType.IntType },
                navArgument("hora") { type = NavType.StringType },
                navArgument("nombrePista") { type = NavType.StringType },
                navArgument("fecha") { type = NavType.StringType },
                navArgument("deporte") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val pistaId = backStackEntry.arguments?.getInt("pistaId") ?: -1
            val hora = backStackEntry.arguments?.getString("hora") ?: ""
            val nombrePista = backStackEntry.arguments?.getString("nombrePista") ?: ""
            val fecha = backStackEntry.arguments?.getString("fecha") ?: ""
            val deporte = backStackEntry.arguments?.getString("deporte") ?: ""

            ReservaFormScreen(
                navController = navController,
                pistaId = pistaId,
                horaSeleccionada = hora,
                nombrePista = nombrePista,
                fechaSeleccionada = fecha,
                deporte = deporte
            )
        }

        //pantalla mis reservas

        composable(
            ScreenNavigation.MisReservas.route
        ) {
            MisReservasScreen(navController = navController)
        }


        //pantalla confirmacion reserva

        composable(
            route = ScreenNavigation.ConfirmacionReserva.route,
            arguments = listOf(
                navArgument("deporte") { type = NavType.StringType },
                navArgument("pistaNombre") { type = NavType.StringType },
                navArgument("hora") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val deporte = backStackEntry.arguments?.getString("deporte") ?: ""
            val pistaNombre = backStackEntry.arguments?.getString("pistaNombre") ?: ""
            val hora = backStackEntry.arguments?.getString("hora") ?: ""

            ConfirmacionReservaScreen(
                navController = navController,
                deporte = deporte,
                pistaNombre = pistaNombre,
                hora = hora
            )
        }

        //Pantalla registro

        composable(
            route = ScreenNavigation.Registro.route
        ){
            RegistroScreen(navController = navController)
        }

        //pantalla perfil
        composable(
            route = ScreenNavigation.Perfil.route
        ){
            PerfilScreen(navController = navController)
        }

        //Ayuda
        composable(
            route = ScreenNavigation.Ayuda.route
        ){
            AyudaScreen(navController = navController)
        }


    }

}