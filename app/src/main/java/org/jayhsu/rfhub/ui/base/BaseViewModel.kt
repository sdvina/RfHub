package org.jayhsu.rfhub.ui.base

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.jayhsu.rfhub.ui.util.Message
import java.util.UUID

data class BaseViewState(
    val messages: List<Message> = emptyList()
)

open class BaseViewModel : ViewModel() {
    private var _state = MutableStateFlow(BaseViewState())
    val sate: StateFlow<BaseViewState>
        get() = _state

    fun showMessage(@StringRes messageId: Int, formatArgs: Array<Any>){
        _state.update { currentState ->
            val messages = currentState.messages + Message(
                id = UUID.randomUUID().mostSignificantBits,
                messageId = messageId,
                formatArgs = formatArgs
            )
            currentState.copy(messages = messages)
        }
    }

    fun messageShown(id: Long) {
        _state.update { currentUiState ->
            val messages = currentUiState.messages.filterNot { it.id == id }
            currentUiState.copy(messages = messages)
        }
    }
}