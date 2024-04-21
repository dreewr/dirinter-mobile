package andre.dev.ui

import andre.dev.lib.State
import andre.dev.presentation.LoginViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    loginHeaderMessage: String,
    onAction: (LoginAction) -> Unit,
    viewModelProvider: ViewModelProvider.Factory
) {
    val viewModel: LoginViewModel = viewModel(factory = viewModelProvider)
    val loginState by viewModel.uiState.collectAsState(null)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val idState = remember { mutableStateOf("") }
        val passwordState = remember { mutableStateOf("") }

        Text(text = loginHeaderMessage, style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = idState.value,
            onValueChange = { idState.value = it },
            label = { Text("ID") },
            singleLine = true,
            enabled = loginState !is  State.Loading,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii)
        )

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            singleLine = true,
            enabled = loginState !is State.Loading,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(16.dp))

        when (loginState) {
            is State.Loading -> CircularProgressIndicator()
            else -> Button(
                onClick = { viewModel.executeLogin(idState.value, passwordState.value) },

            ) {
                Text("LOGIN")
            }
        }

        if (loginState is State.Failure) {
            Text(
                text = "algo deu errado"/*(loginState as LoginState.Error).message*/,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

    LaunchedEffect(loginState) {
        if (loginState is State.Success) onAction.invoke(LoginAction.OnLoginSuccessful("ID" /*passar o user*/))
    }
}
