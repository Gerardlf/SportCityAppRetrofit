package net.iesochoa.gerardodelafuente.sportcityapp.ui.screens

import android.widget.Space
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import net.iesochoa.gerardodelafuente.sportcityapp.ui.navigation.ScreenNavigation
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorBackground
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorError
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorSuccess
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextPrimary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorTextSecondary
import net.iesochoa.gerardodelafuente.sportcityapp.ui.viewModel.PistasViewModel

//Pantalla de detalle donde se puede reservar
@Composable
fun DetallePistaScreen(
    navController: NavController,
    pistaId: Int,
    nombrePista: String,
    viewModel: PistasViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    val pista = uiState.pistas.firstOrNull { it.id == pistaId }
    //deporte
    val deporte = pista?.deporte ?: "Desconocido"

    //para la hora
    var horaSelec by remember { mutableStateOf("12:00") }
    val horasDisponibles = listOf(
        "10:00", "11:00", "12:00",
        "13:00", "14:00", "15:00",
        "16:00", "17:00", "18:00",
        "19:00", "20:00", "22:00"
    )

    //fecha
    data class OpcionFecha(val label: String, val valor: String, val textoVisible: String)

    val opcionesFecha = remember {
        val hoy = java.time.LocalDate.now()

        val formatterVisible = java.time.format.DateTimeFormatter.ofPattern(
            "d 'de' MMMM",
            java.util.Locale("es", "ES")
        )

        val formatterApi = java.time.format.DateTimeFormatter.ISO_LOCAL_DATE

        (0..8).map { dias ->
            val fecha = hoy.plusDays(dias.toLong())

            val label = when (dias) {
                0 -> "Hoy"
                1 -> "Mañana"
                2 -> "Pasado mañana"
                else -> "+$dias días"
            }

            OpcionFecha(
                label = label,
                valor = fecha.format(formatterApi),
                textoVisible = fecha.format(formatterVisible)
            )
        }
    }

    var fechaSeleccionada by remember { mutableStateOf(opcionesFecha[0]) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
    ) {
        //este espacio es para la imagen!!!!!
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(ColorPrimary)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            //flichita atras
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Flecha hacia atras",
                tint = ColorTextPrimary,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = nombrePista,
                color = ColorTextPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            //tutilo de la ddescripcion
            Text(
                text = "Descripción",
                color = ColorTextPrimary,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = pista?.descripcion ?: "Descripción no disponible",
                color = ColorTextSecondary,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Precio por hora",
                color = ColorTextPrimary,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = pista?.let { "${it.precioHora} €/hora" } ?: "Precio no disponible",
                color = ColorTextSecondary,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Fecha",
                color = ColorTextPrimary,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            //aqui pondre la fecha
            Text(
                text = "Fecha seleccionada: ${fechaSeleccionada.textoVisible}",
                color = ColorTextSecondary,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            opcionesFecha.chunked(3).forEach { filaFechas ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    filaFechas.forEach { opcion ->
                        HoraChip(
                            text = opcion.label,
                            selected = fechaSeleccionada == opcion,
                            onClick = { fechaSeleccionada = opcion },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }


            Spacer(modifier = Modifier.height(24.dp))

            //  HORAS DISPONIBLES
            horasDisponibles.chunked(3).forEach { filasHoras ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    filasHoras.forEach { hora ->
                        HoraChip(
                            text = hora,
                            selected = horaSelec == hora,
                            onClick = { horaSelec = hora },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    navController.navigate(
                        ScreenNavigation.ReservaForm.createRoute(
                            pistaId,
                            hora = horaSelec,
                            nombrePista = nombrePista,
                            fecha = fechaSeleccionada.valor,
                            deporte = deporte
                        )
                    )

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorPrimary
                )
            ) {
                Text(
                    text = "Reservar ahora",
                    color = ColorBackground,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
fun HoraChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        color = if (selected) ColorPrimary else ColorBackground,
        tonalElevation = if (selected) 4.dp else 0.dp
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (selected) ColorBackground else ColorTextPrimary,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )

    }
}

//@Preview
//@Composable
//fun detallaPreview() {
//    val navController = rememberNavController()
//
//    DetallePistaScreen(
//        navController = navController,
//        pistaId = 1
//    )
//}
