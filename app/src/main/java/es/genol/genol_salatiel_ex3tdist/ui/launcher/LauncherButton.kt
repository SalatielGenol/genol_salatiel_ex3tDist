package es.genol.genol_salatiel_ex3tdist.ui.launcher

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LauncherButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = AbsoluteCutCornerShape(
            topLeft = 0.dp,
            topRight = 35.dp,
            bottomLeft = 35.dp,
            bottomRight = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        contentPadding = PaddingValues(horizontal = 35.dp)
    ) {
        Text(
            text = label,
        )
    }
}