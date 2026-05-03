package net.iesochoa.gerardodelafuente.sportcityapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import net.iesochoa.gerardodelafuente.sportcityapp.ui.navigation.ScreenNavigation
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorBackground
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorError
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextSecondary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.TextFieldBackground
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.TextFieldBorder
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.TextFieldText
import net.iesochoa.gerardodelafuente.sportcityapp.ui.viewModel.ReservasViewModel

// pantalla de formulario de la reserva
@Composable
fun ReservaFormScreen(
    navController: NavController,
    pistaId: Int,
    horaSeleccionada: String,
    nombrePista: String,
    fechaSeleccionada: String,
    deporte: String,
    viewModel: ReservasViewModel = viewModel()
) {


    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var comentario by remember { mutableStateOf("") }
    var nombreError by remember { mutableStateOf<String?>(null) }
    var telefonoError by remember { mutableStateOf<String?>(null) }
    var reservaError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        //  la flecha atrás y e l título
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Volver atrás",
                tint = ColorPrimary,
                modifier = Modifier
                    .clickable { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Reserva de pista",
                color = ColorTextPrimary,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Información de la pista..


        Text(
            text = "Pista seleccionada: $nombrePista",
            color = ColorTextSecondary,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Fecha: $fechaSeleccionada",
            color = ColorTextSecondary,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Hora: $horaSeleccionada",
            color = ColorTextSecondary,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nombre
        Text(
            text = "Nombre completo",
            color = ColorTextPrimary,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = TextFieldBackground,
                unfocusedContainerColor = TextFieldBackground,
                focusedIndicatorColor = ColorPrimary,
                unfocusedIndicatorColor = TextFieldBorder,
                cursorColor = ColorPrimary,
                focusedTextColor = TextFieldText,
                unfocusedTextColor = TextFieldText
            )
        )
        // si hay algun error en el nombre muestro un mensaje al usuario
        nombreError?.let { errorText ->
            Text(
                text = errorText,
                color = ColorError,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Teléfono
        Text(
            text = "Teléfono",
            color = ColorTextPrimary,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
        TextField(
            value = telefono,
            onValueChange = { telefono = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = TextFieldBackground,
                unfocusedContainerColor = TextFieldBackground,
                focusedIndicatorColor = ColorPrimary,
                unfocusedIndicatorColor = TextFieldBorder,
                cursorColor = ColorPrimary,
                focusedTextColor = TextFieldText,
                unfocusedTextColor = TextFieldText
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            )
        )
        //si hay algun error en el telefono muestro un mensaje de error
        telefonoError?.let { errorText ->
            Text(
                text = errorText,
                color = ColorError,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Comentario
        Text(
            text = "Comentario (opcional)",
            color = ColorTextPrimary,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
        TextField(
            value = comentario,
            onValueChange = { comentario = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            singleLine = false,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = TextFieldBackground,
                unfocusedContainerColor = TextFieldBackground,
                focusedIndicatorColor = ColorPrimary,
                unfocusedIndicatorColor = TextFieldBorder,
                cursorColor = ColorPrimary,
                focusedTextColor = TextFieldText,
                unfocusedTextColor = TextFieldText
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        reservaError?.let { errorText ->
            Text(
                text = errorText,
                color = ColorError,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = {

                //reinicio los errores
                nombreError = null
                telefonoError = null
                reservaError = null

                var hayUnError = false

                //compruebo nombre y telefofno
                if (nombre.isBlank()) {
                    nombreError = "El nombre es obligatorio"
                    hayUnError = true
                }
                if (telefono.isBlank()) {
                    telefonoError = "El teléfono es obligatorio"
                    hayUnError = true
                }
                if (hayUnError) {
                    return@Button
                }

                // 5. Creao la reserva en el ViewModel - repositorio
                viewModel.crearReserva(
                    pistaId = pistaId,
                    pistaNombre = nombrePista,
                    fecha = fechaSeleccionada,
                    hora = horaSeleccionada,
                    nombreCliente = nombre,
                    telefonoCliente = telefono,
                    comentario = comentario.ifBlank { null },
                    deporte = deporte,
                    onSuccess = {
                        navController.navigate(
                            ScreenNavigation.ConfirmacionReserva.crearRuta(
                                deporte = deporte,
                                pistaNombre = nombrePista,
                                hora = horaSeleccionada
                            )
                        )
                    },
                    onError = { mensaje ->
                        reservaError = mensaje
                    }

                )

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorPrimary
            )
        ) {
            Text(
                text = "Confirmar reserva",
                color = ColorBackground,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ReservaFormScreenPreview() {
//    val navController = rememberNavController()
//    ReservaFormScreen(
//        navController = navController,
//        pistaId = 1
//    )
//}






