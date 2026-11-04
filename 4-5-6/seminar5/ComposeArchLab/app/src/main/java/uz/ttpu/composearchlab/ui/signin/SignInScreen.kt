package uz.ttpu.composearchlab.ui.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignInRoute(viewModel: SignInViewModel = viewModel()) {
    val uiState = viewModel.uiState.value
    val snackbarHostState = remember { SnackbarHostState() }

    // Bug from Task 8: after rotation the same Snackbar appeared again, because the ViewModel
    // survived the rotation and still held the Error state, and the recreated composition ran
    // this effect again. Calling onErrorShown() fixes it because the state goes back to SignedOut.
    LaunchedEffect(uiState) {
        if (uiState is SignInUiState.Error) {
            snackbarHostState.showSnackbar(uiState.message)
            viewModel.onErrorShown()
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { innerPadding ->
        SignInScreen(uiState, viewModel::onSignIn, Modifier.padding(innerPadding))
    }
}

@Composable
fun SignInScreen(
    uiState: SignInUiState,
    onSignIn: (email: String, password: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        when (uiState) {
            is SignInUiState.SignedIn -> Text("Welcome, ${uiState.email}")

            SignInUiState.InProgress -> {
                SignInForm(
                    email = email,
                    password = password,
                    enabled = false,
                    onEmailChange = { email = it },
                    onPasswordChange = { password = it },
                    onSignIn = { onSignIn(email, password) }
                )
                CircularProgressIndicator()
            }

            SignInUiState.SignedOut, is SignInUiState.Error -> SignInForm(
                email = email,
                password = password,
                enabled = true,
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onSignIn = { onSignIn(email, password) }
            )
        }
    }
}

@Composable
private fun SignInForm(
    email: String,
    password: String,
    enabled: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignIn: () -> Unit
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Email") }
    )
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation()
    )
    Button(onClick = onSignIn, enabled = enabled) {
        Text("Sign in")
    }
}

@Preview(showBackground = true)
@Composable
private fun SignedOutPreview() {
    SignInScreen(SignInUiState.SignedOut, { _, _ -> })
}

@Preview(showBackground = true)
@Composable
private fun InProgressPreview() {
    SignInScreen(SignInUiState.InProgress, { _, _ -> })
}

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    SignInScreen(SignInUiState.Error("Wrong email or password"), { _, _ -> })
}

@Preview(showBackground = true)
@Composable
private fun SignedInPreview() {
    SignInScreen(SignInUiState.SignedIn("amin@ttpu.uz"), { _, _ -> })
}
