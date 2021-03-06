package net.saga.a16_bitstudio.data

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import android.util.Log
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.core.provider.DocumentsContractCompat
import androidx.documentfile.provider.DocumentFile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.saga.a16_bitstudio.util.CachingDocumentFile
import net.saga.a16_bitstudio.util.toCachingList
import java.io.File
import java.io.FileDescriptor

class ContextProjectRepository(
    val context: Context,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProjectRepository {


    override fun getDefaultDirectory(): Uri {
        return Uri.parse("content://com.android.externalstorage.documents/tree/primary%3ADownload%2FGame")
    }

    override suspend fun getFilesInDirectory(uri: Uri): List<CachingDocumentFile> =
        withContext(defaultDispatcher) {

            if (!DocumentsContract.isTreeUri(uri)) {
                Log.i("ContextProjectRepository", "Not a directory ${uri}")
                emptyList<CachingDocumentFile>()
            } else {

                Log.i("ContextProjectRepository", "getFilesInDirectory ${uri}")
                val documentsTree = DocumentFile.fromTreeUri(context, uri)
                if (documentsTree != null && documentsTree.canRead()) {
                    val childDocuments = documentsTree.listFiles().toCachingList()
                    childDocuments.sortedBy { it.name }
                } else {
                    emptyList<CachingDocumentFile>()
                }
            }
        }


}