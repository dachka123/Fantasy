package com.example.fantastika.LoginRegister.Presentation.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fantastika.LoginRegister.Domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface LoginUiEvent {
    data class LoginSuccess(val token: String,val username: String) : LoginUiEvent
    object NavigateToRegister : LoginUiEvent
}
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _eventChannel = Channel<LoginUiEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()


    fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                // UPDATE: Receive AuthResult
                val result = loginUseCase(username, password)

                // Update state with token
                _state.update { it.copy(isLoading = false, isSuccess = true, accessToken = result.accessToken) }

                // UPDATE: Send token AND username in the success event
                _eventChannel.send(LoginUiEvent.LoginSuccess(result.accessToken, result.username))

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}