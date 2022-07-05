package net.saga.a16_bitstudio.data

import android.net.Uri
import android.util.Log
import java.io.File

interface ProjectRepository {
    fun getDefaultDirectory(): Uri
    suspend fun getFilesInDirectory(uri : Uri): List<File>
}

class StubProjectRepository() : ProjectRepository{
    override fun getDefaultDirectory(): Uri {
        return Uri.fromParts("file", "/tmp", "/")
    }

    override suspend fun getFilesInDirectory(uri: Uri): List<File> {
        Log.i("StubProjectRepository", "getFilesInDirectory(${uri}");
        return listOf(File("Test1"),File("Test2"),File("Test3"))
    }

}