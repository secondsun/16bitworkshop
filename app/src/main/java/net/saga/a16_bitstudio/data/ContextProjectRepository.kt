package net.saga.a16_bitstudio.data

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.net.toFile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class ContextProjectRepository(
    val context: Context,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProjectRepository {
    override fun getDefaultDirectory(): Uri {
        Log.i("ContextProjectRepository", "getDefaultDirectory ${context.getExternalFilesDir(null)}")
        return Uri.fromFile(context.getExternalFilesDir(null)?:context.filesDir)
    }

    override suspend fun getFilesInDirectory(uri: Uri): List<File> =
        withContext(defaultDispatcher) {
            Log.i("ContextProjectRepository", "getFilesInDirectory ${uri}")
            val file = uri.toFile()
            if (!file.isDirectory) {
                emptyList<File>()
            } else {
                Log.i("ContextProjectRepository", "${file.listFiles()?.toList()}")
                file.listFiles()?.toList() ?: emptyList()
            }
        }
}