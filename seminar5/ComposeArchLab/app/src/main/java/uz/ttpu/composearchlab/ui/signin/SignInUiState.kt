package uz.ttpu.composearchlab.ui.signin

// sealed interface and not enum because the cases carry different data (message, email),
// an enum cannot do that
sealed interface SignInUiState {
    data object SignedOut : SignInUiState
    data object InProgress : SignInUiState
    data class Error(val message: String) : SignInUiState
    data class SignedIn(val email: String) : SignInUiState
}
