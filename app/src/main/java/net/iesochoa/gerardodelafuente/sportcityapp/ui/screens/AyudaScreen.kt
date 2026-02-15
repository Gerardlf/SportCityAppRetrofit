package net.iesochoa.gerardodelafuente.sportcityapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.iesochoa.gerardodelafuente.sportcityapp.R
import net.iesochoa.gerardodelafuente.sportcityapp.ui.Components.BottomNavBar
import net.iesochoa.gerardodelafuente.sportcityapp.ui.navigation.ScreenNavigation
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorBackground
import net.iesochoa.gerardodelafuente.sportcityapp.ui.theme.ColorPrimary

@Composable
fun AyudaScreen(
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
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text= stringResource(R.string.proximamente),
                color = ColorPrimary,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }


        //
        BottomNavBar(navController = navController,
            selectedScreen = ScreenNavigation.Ayuda,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp))
    }
}