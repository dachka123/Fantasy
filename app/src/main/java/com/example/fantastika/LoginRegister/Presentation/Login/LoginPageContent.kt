package com.example.fantastika.LoginRegister.Presentation.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.fantastika.Common.Dimens
import com.example.fantastika.LoginRegister.Presentation.Common.AuthCard
import com.example.fantastika.R

@Composable
fun LoginPageContent(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: (token: String,username: String) -> Unit,
) {
    val cyan = Color(0xFF00BCD4)
    val lightBlue = Color(0xFF2196F3)
    val borderGray = Color(0xFF2A3B4C)
    val viewModel: LoginViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is LoginUiEvent.LoginSuccess -> {
                    onLoginSuccess(event.token,event.username)
                }
                is LoginUiEvent.NavigateToRegister -> {
                    // (Optional) If you were using the VM to trigger register navigation
                }
            }
        }
    }

    Box(
        Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.registerback),
            contentDescription = "Register Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        AuthCard(modifier.padding(24.dp)) {

            var usernameText by remember { mutableStateOf(state.username) }
            var passwordText by remember { mutableStateOf(state.password) }
            var passwordVisible by remember { mutableStateOf(false) }

            Text(
                text = "Login Here",
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )

            OutlinedTextField(
                value = usernameText,
                onValueChange = { usernameText = it },
                label = { Text("username") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Email Icon",
                        tint = cyan
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = cyan,
                    unfocusedBorderColor = borderGray,
                    focusedLabelColor = cyan,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = passwordText,
                onValueChange = { passwordText = it },
                label = { Text("Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Key,
                        contentDescription = null,
                        tint = cyan
                    )
                },
                trailingIcon = {
                    val icon =
                        if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = null, tint = cyan)
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = cyan,
                    unfocusedBorderColor = borderGray,
                    focusedLabelColor = cyan,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )


            Button(
                onClick = { viewModel.login(usernameText, passwordText) },
                enabled = !state.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(lightBlue, cyan)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Enter",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            state.error?.let {
                Text(it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
            }

            Row(
                modifier.padding(top = Dimens.spacing10)
            ) {
                Text(
                    text = "Don't have an account?",
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = "Register",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable { onNavigateToRegister() }
                )
            }
        }
    }
}
