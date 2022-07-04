package net.saga.a16_bitstudio.ui

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import net.saga.a16_bitstudio.data.ContextProjectRepository
import net.saga.a16_bitstudio.ui.picker.PickerViewModel
import net.saga.a16_bitstudio.ui.theme.SixteenbitStudioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(context : Context) {
    var pickerVM = viewModel(modelClass = PickerViewModel::class.java, factory = PickerViewModel.provideFactory(ContextProjectRepository(context.applicationContext)))

    SixteenbitStudioTheme {
        // A surface container using the 'background' color from the theme
        Scaffold(topBar = {StudioAppBar()}) {
            Column(modifier = Modifier
                .padding(it)
                .fillMaxSize()) {
                ProjectPicker(pickerVM)
            }
        }
    }

}