package net.iesochoa.gerardodelafuente.sportcityapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.iesochoa.gerardodelafuente.sportcityapp.ui.Components.BottomNavBar
import net.iesochoa.gerardodelafuente.sportcityapp.ui.navigation.ScreenNavigation
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorBackground
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorSecondary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorSuccess
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextSecondary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorWarning

//Pantalla de inicio con los deportes disponibles

@Composable
fun HomeScreen(
    navController: NavController
) {
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
                    .padding(top = 60.dp, bottom = 24.dp, start = 24.dp, end = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                //TextoSport city
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Sport",
                        color = ColorPrimary,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "City",
                        color = ColorSecondary,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Inicio",
                    color = ColorTextPrimary,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(20.dp))

                //Frase
                Text(
                    text = "\"Tu mejor partido empieza aquí.\nReserva y conquista la pista\"",
                    color = ColorTextSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "¿Que te apetece jugar hoy?",
                    color = ColorTextPrimary,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))

                //futbol
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ColorSuccess   //transparent
                    ),
                    onClick = {
                        navController.navigate(
                            ScreenNavigation.PistasDeporte.createRoute("Futbol 7")
                        )
                    }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        //                    Image(
                        //                        painter = painterResource(R.drawable.futbol),
                        //                        contentDescription = "Pistas de futbol",
                        //                        modifier = Modifier.matchParentSize(),
                        //                        contentScale = ContentScale.Crop
                        //                    )

                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .background(Color.Black.copy(alpha = 0.25f))
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .padding(start = 16.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.SportsSoccer,
                                    contentDescription = "Icono fútbol",
                                    tint = ColorBackground
                                )

                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Fútbol 7",
                                    color = Color.White,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                // TENIS
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ColorWarning   // naranja
                    ),
                    onClick = {
                        navController.navigate(ScreenNavigation.PistasDeporte.createRoute("tenis"))
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.SportsTennis,
                                contentDescription = "Icono tenis",
                                tint = ColorBackground
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = "Tenis",
                                color = ColorBackground,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // PADEL
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ColorPrimary  // azul
                    ),
                    onClick = {
                        navController.navigate(
                            ScreenNavigation.PistasDeporte.createRoute("Padel")
                        )
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.SportsTennis,   // reutilizamos tenis para padel
                                contentDescription = "Icono pádel",
                                tint = ColorBackground
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = "Pádel",
                                color = ColorBackground,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                // BASKETBALL
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ColorSecondary   // morado
                    ),
                    onClick = {
                        navController.navigate(
                            ScreenNavigation.PistasDeporte.createRoute("Basket")
                        )
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.SportsBasketball,
                                contentDescription = "Icono baloncesto",
                                tint = ColorBackground
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = "Basketball",
                                color = ColorBackground,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
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
//fun HomeScreenPreview() {
//    HomeScreen()
//}
//@Preview(showBackground = true)
//@Composable
//fun FutbolImagePreview() {
//    Box(
//        modifier = Modifier
//            .size(120.dp)
//            .background(Color.Gray),
//        contentAlignment = Alignment.Center
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.futbolinicio),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//    }
//}