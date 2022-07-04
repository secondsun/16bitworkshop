package net.saga.a16_bitstudio.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toFile
import androidx.core.net.toUri
import net.saga.a16_bitstudio.ui.picker.PickerDirectory
import net.saga.a16_bitstudio.ui.picker.PickerViewModel
import net.saga.a16_bitstudio.ui.theme.SixteenbitStudioTheme
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectPicker(viewModel: PickerViewModel = viewModel()) {

    Scaffold(topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Open Project")
                }
            },
            bottomBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    Text("File System")
                    Text("Clone Git ")
                }
            },

    ) {padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()) {
            Row(Modifier.background(androidx.compose.ui.graphics.Color.Red)) {
                Text("Top")
            }
            PickerDirectory(viewModel.up,
                {if (viewModel.up.name != "") {
                    viewModel.refresh(viewModel.up.toUri())
                }})
            viewModel.files.value.map {
                PickerDirectory(it) {
                    if (it.toFile().isDirectory) {
                        Log.i("ProjectPricker","ProjectPicker Directory on Click ${it}")
                        viewModel.refresh(it)
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun ProjectPickerPreview() {
    SixteenbitStudioTheme {
        ProjectPicker()
    }
}