package net.saga.a16_bitstudio.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppViewModel : ViewModel() {



    val provider = object : ViewModelProvider.Factory  {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AppViewModel() as T
        }

    }

}
