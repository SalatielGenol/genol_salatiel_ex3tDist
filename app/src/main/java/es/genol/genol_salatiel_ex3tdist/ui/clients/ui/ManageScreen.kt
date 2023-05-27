package es.genol.genol_salatiel_ex3tdist.ui.clients.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.genol.genol_salatiel_ex3tdist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageScreen(clientViewModel: ClientViewModel) {
    val listState = rememberLazyListState()

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(R.string.listado_de_clientes)) })
    }, floatingActionButton = {
        if (clientViewModel.clientsData.any { it.checked }) {
            FloatingButtonItemsClose {
                clientViewModel.delClientsRowChecked()
            }
        }
    }, floatingActionButtonPosition = FabPosition.Center) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 15.dp),
            state = listState
        ) {
            items(
                items = clientViewModel.clientsData,
                key = { clientViewModel.clientsData.indexOf(it) }) { client ->
                ClientListItem(
                    client = client.user,
                    email = client.email,
                    password = client.password,
                    checked = client.checked,
                    modifier = Modifier.padding(bottom = 15.dp),
                    onCheckedChange = { clientViewModel.checkItem(client) },
                    onCloseClick = { clientViewModel.delClient(element = client) }
                )
            }
        }
    }
}

