package net.saga.a16_bitstudio.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toFile
import net.saga.a16_bitstudio.ui.picker.FileLineItem
import net.saga.a16_bitstudio.ui.picker.FileViewerVM
import net.saga.a16_bitstudio.ui.theme.SixteenbitStudioTheme
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileViewer(viewModel: FileViewerVM = viewModel()) {

            viewModel.files.value.map {
                FileLineItem(it) {
                    if (it.toFile().isDirectory) {
                        Log.i("ProjectPricker", "ProjectPicker Directory on Click ${it}")
                        viewModel.refresh(it)
                    }
                }
            }
        }


@Composable
@Preview
fun ProjectPickerPreview() {
    SixteenbitStudioTheme {
        Column(Modifier.fillMaxSize()) {
            FileViewer()
        }
    }
}