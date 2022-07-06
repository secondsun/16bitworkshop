package net.saga.a16_bitstudio.data

import android.net.Uri
import android.util.Log
import androidx.documentfile.provider.DocumentFile
import net.saga.a16_bitstudio.util.CachingDocumentFile
import java.io.File

interface ProjectRepository {
    fun getDefaultDirectory(): Uri
    suspend fun getFilesInDirectory(uri : Uri): List<CachingDocumentFile>
}

class StubProjectRepository() : ProjectRepository{
    override fun getDefaultDirectory(): Uri {
        return Uri.fromParts("file", "/tmp", "/")
    }

    override suspend fun getFilesInDirectory(uri: Uri): List<CachingDocumentFile> {
        Log.i("StubProjectRepository", "getFilesInDirectory(${uri}");
        return listOf(
            CachingDocumentFile(DocumentFile.fromFile(File("Test 1"))),
            CachingDocumentFile(DocumentFile.fromFile(File("Test 2"))),
            CachingDocumentFile(DocumentFile.fromFile(File("Test 3"))))
    }

}