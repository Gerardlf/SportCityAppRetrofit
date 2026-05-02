package net.iesochoa.gerardodelafuente.sportcityapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import net.iesochoa.gerardodelafuente.sportcityapp.ui.Components.BottomNavBar
import net.iesochoa.gerardodelafuente.sportcityapp.ui.Components.PistaCard
import net.iesochoa.gerardodelafuente.sportcityapp.ui.navigation.ScreenNavigation
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorBackground
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorError
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorSecondary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorSuccess
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorWarning
import net.iesochoa.gerardodelafuente.sportcityapp.ui.viewModel.PistasViewModel

//Pantalla que pinta las pistas segun el deporte elegido en home
@Composable
fun PistasDeporteScreen(
    navController: NavController,
    deporte: String,
    viewModel: PistasViewModel
) {
    val uiState by viewModel.uiState.collectAsState()


    LaunchedEffect(deporte) {
        viewModel.cargarPistas(deporte)
    }
//Color para las cards
    val cardColor = when (deporte.lowercase()) {
        "tenis" -> ColorWarning
        "fútbol 7", "futbol 7" -> ColorSuccess
        "pádel", "padel" -> ColorPrimary
        "basket" -> ColorSecondary
        else -> ColorWarning
    }
    //Icono para las cards
    val sportIcon = when (deporte.lowercase()) {
        "tenis" -> Icons.Filled.SportsTennis
        "fútbol 7", "futbol 7" -> Icons.Filled.SportsSoccer
        "pádel", "padel" -> Icons.Filled.SportsTennis
        "basket" -> Icons.Filled.SportsBasketball
        else -> Icons.Filled.SportsTennis
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {

                //Flecha atras
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver atras",
                    tint = ColorPrimary,
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Spacer(modifier = Modifier.height(40.dp))
                //Titulo
                Text(
                    text = "Pistas $deporte",
                    color = ColorTextPrimary,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(40.dp))

                //Aqui deberia salir un spinner si esta cargando los datos

                when {
                    uiState.isLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                CircularProgressIndicator(
                                    color = ColorPrimary
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Cargando pistas...",
                                    color = ColorSecondary,
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    uiState.errorMessage != null -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = uiState.errorMessage ?: "Error al cargar las pistas",
                                    color = ColorError,
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = {
                                        viewModel.cargarPistas(deporte)
                                    }
                                ) {
                                    Text("Reintentar")
                                }
                            }
                        }
                    }

                    uiState.pistas.isEmpty() -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No hay pistas disponibles para $deporte",
                                color = ColorSecondary,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(uiState.pistas) { pista ->
                                PistaCard(
                                    pista = pista,
                                    backgroundColor = cardColor,
                                    icono = sportIcon,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(90.dp)
                                        .padding(vertical = 8.dp),
                                    onClick = {
                                        navController.navigate(
                                            ScreenNavigation.DetallePista.crearRuta(
                                                pista.id,
                                                nombrePista = pista.nombre
                                            )
                                        )
                                    }

                                )
                            }
                        }
                    }
                }
            }
        }
        BottomNavBar(
            navController = navController,
            selectedScreen = ScreenNavigation.Home,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
        )
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PistasTenisScreenPreview() {
//    PistasTennisScreen()
//}