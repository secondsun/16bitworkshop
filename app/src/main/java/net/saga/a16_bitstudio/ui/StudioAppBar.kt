package net.saga.a16_bitstudio.ui

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import net.saga.a16_bitstudio.ui.theme.SixteenbitStudioTheme

@Composable
fun StudioAppBar(startOpenIntent:  () -> Unit = {}) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.primary)) {
        IconButton(onClick = { startOpenIntent() }) {
            Icon( painterResource(id = net.saga.a16_bitstudio.R.drawable.ic_open), "Open")
        }
    }
}

@Preview
@Composable
fun preview() {
    SixteenbitStudioTheme() {
        StudioAppBar()
    }
}
