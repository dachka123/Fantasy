package com.example.fantastika.LoginRegister.Presentation.Register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.fantastika.LoginRegister.Presentation.Common.AuthCard
import com.example.fantastika.R

@Composable
fun RegisterPageContent(
    modifier: Modifier = Modifier,
    onRegistrationSuccess: () -> Unit,
) {
    val cyan = Color(0xFF00BCD4)
    val lightBlue = Color(0xFF2196F3)
    val borderGray = Color(0xFF2A3B4C)

    val viewModel: RegisterViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = state.isSuccess) {
        if (state.isSuccess) {
            onRegistrationSuccess()
        }
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.registerback),
            contentDescription = "Register Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        AuthCard(modifier.padding(24.dp)) {

            Text(
                text = "Register Here",
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )

            //username
            OutlinedTextField(
                value = state.username,
                onValueChange = viewModel::updateUsername,
                label = { Text("username") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
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

            //gmail
            OutlinedTextField(
                value = state.email,
                onValueChange = viewModel::updateEmail,
                label = { Text("gmail") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = if (state.emailError != null) Color.Red else cyan
                    )
                },
                isError = state.emailError != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (state.emailError != null) Color.Red else cyan,
                    unfocusedBorderColor = if (state.emailError != null) Color.Red else borderGray,
                    focusedLabelColor = if (state.emailError != null) Color.Red else cyan,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            //password
            OutlinedTextField(
                value = state.password,
                onValueChange = viewModel::updatePassword,
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

            //confirmPassword
            OutlinedTextField(
                value = state.confirmPassword,
                onValueChange = viewModel::updateConfirmPassword,
                label = { Text("confirm password") },
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
                onClick = { viewModel.register() },
                enabled = !state.isLoading && state.emailError == null,
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

            if (state.isSuccess) {
                Text("Registration successful! Redirecting to login...", color = Color.Green, modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}