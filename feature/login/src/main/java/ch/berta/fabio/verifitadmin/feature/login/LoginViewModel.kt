package ch.berta.fabio.verifitadmin.feature.login

import android.content.Intent
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.berta.fabio.verifitadmin.component.auth.LaunchSignIn
import ch.berta.fabio.verifitadmin.component.auth.ProcessSignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
internal class LoginViewModel
@Inject
constructor(
    private val launchSignIn: LaunchSignIn,
    private val processSignIn: ProcessSignIn,
) : ViewModel() {

    fun onSignInClick(launch: (request: IntentSenderRequest) -> Unit) {
        viewModelScope.launch { launchSignIn(launch) }
    }

    fun onSignInResult(data: Intent?) {
        viewModelScope.launch { processSignIn(data) }
    }
}
