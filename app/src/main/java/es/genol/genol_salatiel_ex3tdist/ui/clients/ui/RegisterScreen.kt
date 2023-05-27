package es.genol.genol_salatiel_ex3tdist.ui.clients.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import es.genol.genol_salatiel_ex3tdist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(clientViewModel: ClientViewModel) {
    val clientUiState by clientViewModel.uiRegisterState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(R.string.registro)) },
            actions = {
                Row(Modifier.padding(end = 5.dp)) {
                    Text(text = stringResource(R.string.clientes))
                    Text(text = clientViewModel.clientsData.size.toString())
                }
            }
        )
    }) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card {
                Column(
                    Modifier
                        .fillMaxHeight(.7f)
                        .padding(all = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        value = clientUiState.userName,
                        onValueChange = { clientViewModel.validateUserName(it) },
                        label = {
                            Text(
                                text = stringResource(R.string.nombre_de_usuario)
                            )
                        },
                        isError = clientViewModel.isUserTypedValid
                    )
                    OutlinedTextField(
                        value = clientUiState.mailAddress,
                        onValueChange = { clientViewModel.validateEmail(it) },
                        label = {
                            Text(
                                text = stringResource(R.string.email)
                            )
                        },
                        isError = clientViewModel.isEmailTypedValid
                    )
                    OutlinedTextField(
                        value = clientUiState.password,
                        onValueChange = { clientViewModel.validatePassLength(it) },
                        visualTransformation = PasswordVisualTransformation(),
                        label = {
                            Text(
                                text = stringResource(R.string.contrase_a)
                            )
                        },
                        isError = clientViewModel.isPassTypedLengthValid,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    OutlinedTextField(
                        value = clientUiState.passwordConfirm,
                        onValueChange = { clientViewModel.validatePassword(it) },
                        visualTransformation = PasswordVisualTransformation(),
                        label = {
                            Text(
                                text = stringResource(R.string.repetir_contrase_a)
                            )
                        },
                        isError = clientViewModel.isPassTypedValid,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Button(
                        onClick = { clientViewModel.addClient() },
                        enabled = clientViewModel.isSignInEnabled
                    ) {
                        Text(text = stringResource(R.string.sign_in))
                    }
                }
            }
        }
    }

    clientUiState.isUserRegistered?.let { registerConfirm ->
        if (registerConfirm) RegisteredDialog(onConfirm = { clientViewModel.dialogOkConfirm() }) else MatchDialog(
            onConfirm = { clientViewModel.dialogClose() })

    }
}
