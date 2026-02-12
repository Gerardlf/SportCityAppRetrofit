package net.iesochoa.gerardodelafuente.sportcityapp.ui.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import net.iesochoa.gerardodelafuente.sportcityapp.R
import net.iesochoa.gerardodelafuente.sportcityapp.ui.navigation.ScreenNavigation
import net.iesochoa.gerardodelafuente.sportcityapp.ui.viewModel.LoginViewModel
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorBackground
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorError
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorSecondary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextSecondary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.TextFieldBackground
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.TextFieldBorder
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.TextFieldText

//Pantalla del login, usa Auth de firebase con correo y contreseña
@Composable
fun loginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = uiState.isloggedIn) {
        if (uiState.isloggedIn){
            navController.navigate(ScreenNavigation.Home.route){
                //Quito el loggin de la pila de llamadas para que no se pueda volver atras!!!!
                popUpTo(ScreenNavigation.Login.route){
                    inclusive= true
                }
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            var email by remember { mutableStateOf("") }
//            var password by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.height(32.dp))

            //LOGO
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo SportCity",
                modifier = Modifier
                    .height(150.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))


            //Bienvenida

            Text(
                text = stringResource(net.iesochoa.gerardodelafuente.sportcityapp.R.string.bienvenido),
                color = ColorTextPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            //mensaje bienvenida
            Text(
                text = stringResource(net.iesochoa.gerardodelafuente.sportcityapp.R.string.subtitulo_bienvenida),
                color = ColorTextSecondary,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            //login


            Text(
                text = stringResource(net.iesochoa.gerardodelafuente.sportcityapp.R.string.email_usuario),
                color = ColorTextPrimary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = TextFieldBackground,
                border = BorderStroke(1.dp, TextFieldBorder)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //Icono de usuario
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        tint = ColorTextSecondary
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    // Texto placeholder
                    TextField(
                        value = uiState.email,
                        onValueChange = { newEmail ->
                            viewModel.onEmailChanged(newEmail)
                        },
                        modifier = Modifier.weight(1f),

                        placeholder = {
                            Text(
                                text = "Introduce tu email o usuario",
                                color = ColorTextSecondary
                            )
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = TextFieldBackground,
                            unfocusedContainerColor = TextFieldBackground,
                            focusedIndicatorColor = ColorPrimary,
                            unfocusedIndicatorColor = TextFieldBorder,
                            focusedTextColor = TextFieldText,
                            unfocusedTextColor = TextFieldText
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))

            uiState.emailError?.let { errorTexto ->
                Text(
                    text = errorTexto,
                    color = ColorError,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            //contraseña

            Text(
                text = stringResource(net.iesochoa.gerardodelafuente.sportcityapp.R.string.password),
                color = ColorTextPrimary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = TextFieldBackground,
                border = BorderStroke(1.dp, TextFieldBorder)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //Icono de contraseña
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        tint = ColorTextSecondary
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    // Texto placeholder
                    TextField(
                        value = uiState.password,
                        onValueChange = { newPassword -> viewModel.onPasswordChanged(newPassword) },
                        modifier = Modifier.weight(1f),
                        placeholder = {
                            Text(
                                text = "Introduce tu contraseña",
                                color = ColorTextSecondary
                            )
                        },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = TextFieldBackground,
                            unfocusedContainerColor = TextFieldBackground,
                            focusedIndicatorColor = ColorPrimary,
                            unfocusedIndicatorColor = TextFieldBorder,
                            focusedTextColor = TextFieldText,
                            unfocusedTextColor = TextFieldText
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))

            uiState.passwordError?.let { errorTexto ->
                Text(
                    text = errorTexto,
                    color = ColorError,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "¿Olvidaste tu contraseña?",
                color = ColorTextSecondary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¿No tienes cuenta? ",
                    color = ColorSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Regístrate aquí",
                    color = ColorPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable{
                        navController.navigate(ScreenNavigation.Registro.route)
                    }
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    //intentamos hacer login
                    viewModel.onLoginClicked()
                },
                enabled = !uiState.isloading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorPrimary
                )
            ) {
                Text(
                    text = if (uiState.isloading) "Iniciando . . . . . . . " else "Iniciar sesión",
                    color = ColorBackground,
//                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    letterSpacing = 0.5.sp
                )
            }
            //mensaje de error de firebase
            uiState.authenError?.let{ errorTexto ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorTexto,
                    color= ColorError,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }


        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LoginScreenPreview() {
//
//
//    loginScreen()
//}