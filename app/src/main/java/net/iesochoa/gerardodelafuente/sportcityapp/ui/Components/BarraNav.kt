package net.iesochoa.gerardodelafuente.sportcityapp.ui.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.iesochoa.gerardodelafuente.sportcityapp.ui.navigation.ScreenNavigation
import net.iesochoa.gerardodelafuente.sportcityapp.ui.screens.HomeScreen
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorBackground
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextSecondary

//Iconos dela barra de navegación

@Composable
fun NavegItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: ()-> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable{ onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = if (selected) ColorPrimary else ColorTextSecondary
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = if (selected) ColorPrimary else ColorTextSecondary
        )
    }
}

//barra de navegacion del fondo de la pantalla
@Composable
fun BottomNavBar(navController: NavController,
                 selectedScreen: ScreenNavigation,
                 modifier: Modifier

) {
    Surface(
        tonalElevation = 8.dp,
        color = ColorBackground,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Icono inicio
            NavegItem(
                icon = Icons.Filled.Home,
                label = "Inicio",
                selected = selectedScreen == ScreenNavigation.Home,
                onClick = {
                    navController.navigate(ScreenNavigation.Home.route){
                        //para que no se vayan a apilar mucha pantallas home
                        popUpTo(ScreenNavigation.Home.route){
                            inclusive = true
                        }
                    }
                }
            )
            NavegItem(
                icon = Icons.Filled.Event,
                label = "Reservas",
                selected = selectedScreen == ScreenNavigation.MisReservas,
                onClick = {
                    navController.navigate(ScreenNavigation.MisReservas.route)
                }
            )
            NavegItem(
                icon = Icons.Filled.Person,
                label = "Perfil",
                selected = selectedScreen == ScreenNavigation.Perfil,
                onClick = {
                    navController.navigate(ScreenNavigation.Perfil.route){
                        popUpTo (ScreenNavigation.Home.route){
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
            NavegItem(
                icon = Icons.Filled.Help,
                label = "Ayuda",
                selected = selectedScreen == ScreenNavigation.Ayuda,
                onClick = {
                    navController.navigate(ScreenNavigation.Ayuda.route){
                        popUpTo(ScreenNavigation.Home.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun BottomNavBarPreview() {
//    BottomNavBar(modifier = Modifier
//        .fillMaxWidth()
//        .padding(bottom = 16.dp))
//}