package ch.berta.fabio.verifitadmin.feature.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

@Composable
internal fun Login(onSignInClick: () -> Unit) {
    Button(onClick = onSignInClick) {
        Text("Sign-In")
    }
}

const val LOGIN_ROUTE = "login"

fun NavGraphBuilder.login() {
    composable(LOGIN_ROUTE) {
        val viewModel = hiltViewModel<LoginViewModel>()
        val activityLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result -> viewModel.onSignInResult(result.data) }
        Login(onSignInClick = { viewModel.onSignInClick(activityLauncher::launch) })
    }
}

fun NavController.navigateToLogin() {
    navigate(LOGIN_ROUTE)
}

@Preview
@Composable
private fun LoginPreview() {
    Login(onSignInClick = { /* noop */ })
}