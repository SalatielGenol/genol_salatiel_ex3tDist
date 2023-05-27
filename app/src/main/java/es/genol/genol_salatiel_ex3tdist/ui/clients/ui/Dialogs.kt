package es.genol.genol_salatiel_ex3tdist.ui.clients.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import es.genol.genol_salatiel_ex3tdist.R

@Composable
fun RegisteredDialog(onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onConfirm() },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = stringResource(R.string.aceptar))
            }
        },
        title = { Text(text = stringResource(R.string.usuario_a_adido)) },
        text = { Text(text = stringResource(R.string.usuario_registrado)) }
    )
}

@Composable
fun MatchDialog(onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onConfirm() },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = stringResource(R.string.aceptar))
            }
        },
        title = { Text(text = stringResource(R.string.usuario_existente)) },
        text = { Text(text = stringResource(R.string.usuario_no_valido)) }
    )
}