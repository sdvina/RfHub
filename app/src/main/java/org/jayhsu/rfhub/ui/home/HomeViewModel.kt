package org.jayhsu.rfhub.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.jayhsu.rfhub.ui.base.BaseViewModel

data class HomeViewState(
    val dummy: String
)
class HomeViewModel(): BaseViewModel() {
    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel() as T
            }
        }
    }
}