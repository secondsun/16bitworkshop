package net.saga.a16_bitstudio.ui

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import net.saga.a16_bitstudio.MainActivity
import net.saga.a16_bitstudio.data.ContextProjectRepository
import net.saga.a16_bitstudio.ui.picker.FileViewerVM
import net.saga.a16_bitstudio.ui.theme.SixteenbitStudioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(activity: MainActivity, lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {

    val contentResolver = activity.contentResolver

    val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION

    var pickerVM = viewModel(
        modelClass = FileViewerVM::class.java,
        factory = FileViewerVM.provideFactory(ContextProjectRepository(activity.applicationContext))
    )

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocumentTree()) {
        if (it != null) {
            contentResolver.takePersistableUriPermission(it, takeFlags)
            pickerVM.refresh(it)
        }
    }

    SixteenbitStudioTheme {
        // A surface container using the 'background' color from the theme
        Scaffold(topBar = { StudioAppBar(startOpenIntent = { launcher.launch(pickerVM.directoryUri.value) }) }) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                FileViewer(pickerVM)
            }
        }
    }

}