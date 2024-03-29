package ch.berta.fabio.verifitadmin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ch.berta.fabio.core.theme.Theme
import ch.berta.fabio.verifitadmin.component.auth.AuthState
import ch.berta.fabio.verifitadmin.feature.clients.ClientsRoute
import ch.berta.fabio.verifitadmin.feature.clients.clients
import ch.berta.fabio.verifitadmin.feature.clients.navigateToClients
import ch.berta.fabio.verifitadmin.feature.login.LoginRoute
import ch.berta.fabio.verifitadmin.feature.login.login
import ch.berta.fabio.verifitadmin.feature.login.navigateToLogin
import ch.berta.fabio.verifitadmin.feature.trainings.TrainingsRoute
import ch.berta.fabio.verifitadmin.feature.trainings.navigateToTrainings
import ch.berta.fabio.verifitadmin.feature.trainings.trainings
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun App() {
    Theme {
        Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
            val navController = rememberNavController()
            Scaffold(
                topBar = { AppBar() },
                bottomBar = { BottomNavigationBar(navController = navController) }
            ) { padding ->
                val viewModel = hiltViewModel<AppViewModel>()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                AppNavHost(
                    navController = navController,
                    authState = uiState.authState,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        colors =
            TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        title = { Text(text = "veri-fit Admin", maxLines = 1, overflow = TextOverflow.Ellipsis) },
        modifier = modifier,
    )
}

@Composable
private fun BottomNavigationBar(navController: NavHostController, modifier: Modifier = Modifier) {
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        NavigationBarItem(
            icon = { Icon(Icons.Filled.DateRange, contentDescription = null) },
            label = { Text(stringResource(R.string.trainings)) },
            selected = currentDestination.isRouteInHierarchy(TrainingsRoute),
            onClick = { navController.navigateToTrainings() }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = null) },
            label = { Text(stringResource(R.string.clients)) },
            selected = currentDestination.isRouteInHierarchy(ClientsRoute),
            onClick = { navController.navigateToClients() }
        )
    }
}

private fun NavDestination?.isRouteInHierarchy(route: String): Boolean =
    this?.hierarchy?.any { it.route == route } ?: false

@Composable
private fun AppNavHost(
    navController: NavHostController,
    authState: AuthState,
    modifier: Modifier = Modifier,
) {
    val isLoggedOut = authState is AuthState.LoggedOut
    val startDestination = if (isLoggedOut) LoginRoute else TrainingsRoute
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        login()
        trainings()
        clients()
    }

    LaunchedEffect(navController, isLoggedOut) {
        navController.currentBackStackEntryFlow
            .filter { isLoggedOut && it.destination.route != LoginRoute }
            .onEach { navController.navigateToLogin() }
            .launchIn(this)
    }
}

@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    Theme { App() }
}
