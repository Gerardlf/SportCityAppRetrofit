package net.iesochoa.gerardodelafuente.sportcityapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import net.iesochoa.gerardodelafuente.sportcityapp.ui.Components.BottomNavBar
import net.iesochoa.gerardodelafuente.sportcityapp.ui.navigation.ScreenNavigation
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorBackground
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextSecondary

@Composable
fun PerfilScreen(
    navController: NavController
) {
    //Usuario de firebase
    val user = FirebaseAuth.getInstance().currentUser
    val email = user?.email ?: "Correo no disponible"
    val uid = user?.uid ?: "UID no disponible"

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
                //flechita hacia atras

                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = ColorTextPrimary,
                    modifier = Modifier.clickable{
                        navController.popBackStack()
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                //Titulo
                Text(
                    text = "Mi perfil",
                    color = ColorTextPrimary,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(32.dp))

                Surface(
                    modifier = Modifier
                        .size(90.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = 4.dp
                ) {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                        ){
                        Icon(
                            imageVector = Icons.Filled.PersonOutline,
                            contentDescription = "Usuario",
                            tint = ColorTextPrimary,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))

                //Datos del usuaria
                Text(
                    text = "Correo",
                    color = ColorTextSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = email,
                    color =ColorTextPrimary,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "UID (Firebase)",
                    color = ColorTextSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = uid,
                    color =ColorTextPrimary,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        //Barra navegacion de abajo
        BottomNavBar(
            navController = navController,
            selectedScreen = ScreenNavigation.Perfil,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp)
        )
    }
}