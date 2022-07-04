package net.saga.a16_bitstudio.ui.picker

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.*
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.saga.a16_bitstudio.data.ProjectRepository
import net.saga.a16_bitstudio.data.StubProjectRepository
import java.io.File

class PickerViewModel(
    val projectRepository: ProjectRepository = StubProjectRepository()
) : ViewModel() {


    var directoryUri = mutableStateOf(projectRepository.getDefaultDirectory())
        private set

    var files = mutableStateOf<List<File>>(emptyList())

    val up : File
    get() =  directoryUri.value.toFile().parentFile


    fun refresh(uri: Uri) {
        viewModelScope.launch {
            Log.i("PickerViewModel", "Refresh ${uri}")
            directoryUri.value = uri
            files.value = projectRepository.getFilesInDirectory(uri)
        }
    }

   init {

       Log.i("PickerViewModel", "Init ${directoryUri.value}")

        viewModelScope.launch {
            Log.i("PickerViewModel", "Launch ${directoryUri.value}")
            files.value =  projectRepository.getFilesInDirectory(directoryUri.value)
        }.start()
    }

    companion object {
        fun provideFactory(
            projectRepository: ProjectRepository,
        ): Factory = object : Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PickerViewModel(projectRepository) as T
            }
        }
    }

}

