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
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import net.saga.a16_bitstudio.util.CachingDocumentFile
import java.io.File
import net.saga.a16_bitstudio.R as StudioResources

@Composable
fun FileLineItem(file: CachingDocumentFile, onClick: (uri: Uri) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable(enabled = true,onClick =  {onClick(file.uri)}), horizontalArrangement = Arrangement.Start
    ) {
        if (file.isDirectory) {
            Icon(
                modifier = Modifier.padding(2.dp).align(CenterVertically),
                painter = painterResource(id = StudioResources.drawable.ic_open),
                contentDescription = "Folder"
            )
        }
        Column(modifier = Modifier.padding(2.dp).align(CenterVertically)) {
            FileText(file.name?:"")

        }

    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
private fun FileText(name : String) {
    Text(fontSize = TextUnit(12f, TextUnitType.Sp),text = name)
}