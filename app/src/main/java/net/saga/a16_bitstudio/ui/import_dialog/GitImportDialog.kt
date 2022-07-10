package net.saga.a16_bitstudio.ui.import_dialog

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import net.saga.a16_bitstudio.ui.theme.SixteenbitStudioTheme

@Composable
fun GitImportDialog(open: Boolean = true, onDismissRequest: () -> Unit = {}) {

    var url = remember {
        mutableStateOf("")
    }

    var destination = remember {
        mutableStateOf("")
    }
    if (open) {
        Dialog(onDismissRequest = onDismissRequest) {


            Column() {
                Row() {
                    Text(text = "Url")
                    TextField(value = url.value, onValueChange = { url.value = it })
                }
                Row() {
                    Text(text = "Destination Directory")
                    TextField(value = destination.value, onValueChange = { destination.value = it })
                }
                Row() {
                    Button(onClick = {
                        Log.i(
                            "GIT_IMPORT",
                            "Clone ${url.value} to ${destination.value}"
                        )
                    }
                    ) {
                        Text("Clone")
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun preview() {
    SixteenbitStudioTheme {
        GitImportDialog()
    }
}