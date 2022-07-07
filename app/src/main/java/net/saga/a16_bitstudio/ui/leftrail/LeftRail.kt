package net.saga.a16_bitstudio.ui.leftrail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import net.saga.a16_bitstudio.R
import net.saga.a16_bitstudio.ui.FileViewer
import net.saga.a16_bitstudio.ui.picker.FileViewerVM

@Composable
@Preview
fun LeftRail(filesVM : FileViewerVM = viewModel()) {

    val filesOpen = remember {
        mutableStateOf(false)
    }
    Row(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Blue)) {
        Column(
            Modifier
                .background(Color.Red)
                .fillMaxHeight()) {

            Icon(painterResource(id = R.drawable.ic_open), "File Browser", tint = Color.Black, modifier = Modifier.clickable { filesOpen.value = !filesOpen.value })
            if (filesOpen.value) {
                FileViewer()
            }
        }
    }
}