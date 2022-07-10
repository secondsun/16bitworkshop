package net.saga.a16_bitstudio.ui

import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import net.saga.a16_bitstudio.data.ContextProjectRepository
import net.saga.a16_bitstudio.ui.import_dialog.ImportDialog
import net.saga.a16_bitstudio.ui.leftrail.LeftRail
import net.saga.a16_bitstudio.ui.picker.FileViewerVM
import net.saga.a16_bitstudio.ui.theme.SixteenbitStudioTheme
import net.saga.a16_bitstudio.util.OpenDocumentTreeWithPersistPermission


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(context: Context = LocalContext.current) {

    val contentResolver = context.contentResolver
    val showImportDialog = remember {
        mutableStateOf(false)
    }
    var pickerVM = viewModel(
        modelClass = FileViewerVM::class.java,
        factory = FileViewerVM.provideFactory(ContextProjectRepository(context.applicationContext))
    )

    val launcher = rememberLauncherForActivityResult(OpenDocumentTreeWithPersistPermission()) {
        if (it != null) {
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION

            contentResolver.takePersistableUriPermission(it, takeFlags)

            pickerVM.refresh(it)
        }
    }

    SixteenbitStudioTheme {
        // A surface container using the 'background' color from the theme
        Scaffold(topBar = { StudioAppBar(startOpenIntent = {showImportDialog.value = true}) }) {
            Row(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                LeftRail(pickerVM)
                        //FileViewer(pickerVM)
            }
        }
        ImportDialog(open = showImportDialog.value,
            onDismissRequest = {showImportDialog.value = false},
            onImportFromAndroid = { launcher.launch(pickerVM.directoryUri.value);showImportDialog.value = false } )
    }

}