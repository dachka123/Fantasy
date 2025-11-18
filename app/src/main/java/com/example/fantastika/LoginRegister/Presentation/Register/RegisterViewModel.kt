package com.example.fantastika.LoginRegister.Presentation.Register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fantastika.LoginRegister.Domain.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    fun updateUsername(username: String) = _state.update { it.copy(username = username) }
    fun updateEmail(email: String) {
        // Clear previous error when typing
        _state.update { it.copy(email = email, emailError = null) }

        // Validation check for Gmail format (client-side enforcement)
        if (email.isNotEmpty() && !isValidGmailFormat(email)) {
            _state.update { it.copy(emailError = "Must be a valid @gmail.com address.") }
        }
    }
    fun updatePassword(password: String) = _state.update { it.copy(password = password) }
    fun updateConfirmPassword(confirmPassword: String) = _state.update { it.copy(confirmPassword = confirmPassword) }

    private fun isValidGmailFormat(email: String): Boolean {
        // Check for general email pattern AND explicitly for @gmail.com
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.endsWith("@gmail.com", ignoreCase = true)
    }

    fun register() {
        val currentState = _state.value

        if (currentState.emailError != null) {
            _state.update { it.copy(error = "Please correct the email address format.") }
            return
        }

        if (currentState.password != currentState.confirmPassword) {
            _state.update { it.copy(error = "Passwords do not match.") }
            return
        }

        if (currentState.username.isBlank() || currentState.email.isBlank() || currentState.password.isBlank()) {
            _state.update { it.copy(error = "All fields must be filled out.") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val success = registerUseCase(
                    username = currentState.username,
                    email = currentState.email,
                    password = currentState.password
                )

                if (success) {
                    // Good practice: Clear fields on success for a fresh state (optional)
                    _state.update { it.copy(
                        isLoading = false,
                        isSuccess = true,
                        username = "",
                        email = "",
                        password = "",
                        confirmPassword = ""
                    ) }
                    // In a real app: Use a side effect (like a Channel/SharedFlow) to trigger navigation.
                } else {
                    // This block is now less likely to hit if GraphQL errors are thrown as exceptions.
                    _state.update { it.copy(isLoading = false, error = "Registration failed. Please try again.") }
                }

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.localizedMessage ?: "Network error during registration."
                    )
                }
            }
        }
    }
}