package net.iesochoa.gerardodelafuente.sportcityapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.iesochoa.gerardodelafuente.sportcityapp.R
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorBackground
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorError
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextSecondary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.TextFieldBackground
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.TextFieldBorder

@Composable
fun RegistroScreen(
    navController: NavController

) {

    //estado
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repertirPassword by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
            .padding(horizontal = 25.dp, vertical = 25.dp),

        ) {
        //titulo y flecha atras
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Volver atrás",
                tint = ColorPrimary,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = "Crear cuenta",
                color = ColorTextPrimary,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Regístrate con tu correo y un contraseña",
            color = ColorTextSecondary,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(24.dp))



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            // ------ EMAIL -----

            Text(
                text = stringResource(R.string.correo_electr_nico),
                color = ColorTextPrimary,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = TextFieldBackground, border = BorderStroke(1.dp, TextFieldBorder)
            ) {


                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Introduce tu correo electrónico",
                        tint = ColorTextSecondary
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                            emailError = null
                        },
                        modifier = Modifier.weight(1f),
                        placeholder = {
                            Text(
                                text = "correo@correo.com",
                                color = ColorTextSecondary
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )
                }
            }
            emailError?.let { error ->
                Text(
                    text = error,
                    color = ColorError,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.height(16.dp))


            //------------Password----------------

            Text(
                text = stringResource(R.string.contrase_a),
                color = ColorTextPrimary,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = TextFieldBackground,
                border = BorderStroke(1.dp, TextFieldBorder)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Introduce una contraseña",
                        tint = ColorTextSecondary
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    TextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = null
                        },
                        modifier = Modifier.weight(1f),
                        placeholder = {
                            Text(
                                text = "Minimo 8 caracteres",
                                color = ColorTextSecondary
                            )
                        },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            //-------Repetir la contraseña

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = TextFieldBackground,
                border = BorderStroke(1.dp, TextFieldBorder)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Repite la contraseña",
                        tint = ColorTextSecondary
                    )
                    Spacer(modifier = Modifier.width(12.dp))

                    TextField(
                        value = repertirPassword,
                        onValueChange = {
                            repertirPassword = it
                            passwordError = null
                        },
                        modifier = Modifier.weight(1f),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.repite_la_contrasena),
                                color = ColorTextSecondary
                            )
                        },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        )
                    )
                }
            }
            passwordError?.let { error ->
                Text(
                    text = error,
                    color = ColorError,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            //---------------Boton crear cuenta

            Button(
                onClick = {
                    emailError = null
                    passwordError = null

                    var hayError = false

                    if (email.isBlank()) {
                        emailError = "El correo es obligatorio"
                        hayError = true
                    }
                    if (password.length < 8) {
                        passwordError = "La contraseña debe tener al menos 8 caracteres"
                        hayError = true
                    } else if (password != repertirPassword) {
                        passwordError = "Las contraseñas no coinciden"
                        hayError = true
                    }
                    if (hayError) {
                        return@Button
                    }

                    //llamar a firebase?????
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorPrimary
                )
            ) {
                Text(
                    text = "Crear cuenta",
                    color = ColorBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}



