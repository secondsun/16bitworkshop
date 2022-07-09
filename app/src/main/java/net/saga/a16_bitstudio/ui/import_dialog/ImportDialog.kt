package net.saga.a16_bitstudio.ui.import_dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import net.saga.a16_bitstudio.R

@Composable
@Preview
fun ImportDialog(
    open: Boolean = false,
    onDismissRequest: () -> Unit = {},
    onImportFromAndroid: () -> Unit = {},
    onImportFromGit: () -> Unit = {},
    onOpenProject: () -> Unit = {}
) {

    if (open) {
        Dialog(onDismissRequest = onDismissRequest) {
            SmallTopAppBar(title = { "Import or Open a Project" })
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cloud_download),
                        contentDescription = "Import From Git"
                    )
                    Text("Import From Git")
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.padding(8.dp).clickable { onImportFromAndroid() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_folder),
                        contentDescription = "Import From Filesystem"
                    )
                    Text("Import From Android")
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_open),
                        contentDescription = "Open Imported Project"
                    )
                    Text("Open Project")
                }
            }
        }
    }
}

