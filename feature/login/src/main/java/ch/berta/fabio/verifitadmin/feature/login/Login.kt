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
import ch.berta.fabio.core.theme.Theme

@Composable
internal fun Login(onSignInClick: () -> Unit) {
    Button(onClick = onSignInClick) { Text("Sign-In") }
}

const val LoginRoute = "login"

fun NavGraphBuilder.login() {
    composable(LoginRoute) {
        val viewModel = hiltViewModel<LoginViewModel>()
        val activityLauncher =
            rememberLauncherForActivityResult(
                ActivityResultContracts.StartIntentSenderForResult()
            ) { result ->
                viewModel.onSignInResult(result.data)
            }
        Login(onSignInClick = { viewModel.onSignInClick(activityLauncher::launch) })
    }
}

fun NavController.navigateToLogin() {
    navigate(LoginRoute)
}

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    Theme { Login(onSignInClick = {}) }
}
