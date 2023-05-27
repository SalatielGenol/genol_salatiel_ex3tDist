package es.genol.genol_salatiel_ex3tdist.ui.clients.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import es.genol.genol_salatiel_ex3tdist.R
import es.genol.genol_salatiel_ex3tdist.ui.theme.Pink40
import es.genol.genol_salatiel_ex3tdist.ui.theme.Pink80

@Composable
fun ClientListItem(
    client: String,
    email: String,
    password: String,
    checked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
    onCloseClick: () -> Unit,
) {
    var isShowPasswordRow by rememberSaveable { mutableStateOf(false) }
    var isShowPasswordLetters by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(color = Pink80)
                .clickable { isShowPasswordRow = !isShowPasswordRow }
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(.5f)) {
                Text(text = client)
                Text(text = email)
            }
            Checkbox(
                modifier = Modifier.weight(.2f),
                checked = checked,
                onCheckedChange = {onCheckedChange(it)})
            Icon(
                Icons.Default.Close,
                contentDescription = stringResource(R.string.delete_client),
                Modifier
                    .weight(.1f)
                    .clickable { onCloseClick() })
        }
        if (isShowPasswordRow) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Pink40),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = password,
                    onValueChange = {},
                    enabled = false,
                    visualTransformation = if (isShowPasswordLetters) VisualTransformation.None else PasswordVisualTransformation()
                )
                /*Text(text = password )*/
                Icon(
                    Icons.Default.Info,
                    contentDescription = stringResource(R.string.reveal_the_password),
                    Modifier.clickable { isShowPasswordLetters = !isShowPasswordLetters }
                )
            }
        }


    }
}