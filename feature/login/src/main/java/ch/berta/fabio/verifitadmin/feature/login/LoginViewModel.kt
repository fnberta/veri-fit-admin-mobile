package ch.berta.fabio.verifitadmin.feature.login

import android.content.Intent
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.berta.fabio.verifitadmin.core.auth.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(private val authService: AuthService) : ViewModel() {

    fun onSignInClick(launchSignIn: (request: IntentSenderRequest) -> Unit) {
        viewModelScope.launch {
            val intent = authService.getSignInIntent()
            val request = IntentSenderRequest.Builder(intent).build()
            launchSignIn(request)
        }
    }

    fun onSignInResult(data: Intent?) {
        viewModelScope.launch {
            authService.processSignInData(data)
        }
    }
}