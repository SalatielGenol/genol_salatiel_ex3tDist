package es.genol.genol_salatiel_ex3tdist.ui.clients.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import es.genol.genol_salatiel_ex3tdist.R

@Composable
fun FloatingButtonItemsClose(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            Icons.Filled.Close,
            contentDescription = stringResource(R.string.elimina_varios_registros)
        )
    }
}