package net.saga.a16_bitstudio.ui.picker

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import net.saga.a16_bitstudio.R
import java.io.File

@Composable
fun PickerDirectory(file: File, onClick: (uri: Uri) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .padding(8.dp)
        .clickable(enabled = true,onClick =  {onClick(file.toUri())}), horizontalArrangement = Arrangement.SpaceAround
    ) {
        if (file.isDirectory) {
            Icon(
                modifier = Modifier.align(CenterVertically),
                painter = painterResource(id = R.drawable.ic_open),
                contentDescription = "Folder"
            )
        }
        Column(modifier = Modifier.align(CenterVertically)) {
            Text(file.name)

        }

    }
}
