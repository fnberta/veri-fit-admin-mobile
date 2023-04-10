package ch.berta.fabio.verifitadmin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import ch.berta.fabio.verifitadmin.core.auth.AuthState
import ch.berta.fabio.verifitadmin.feature.clients.CLIENTS_ROUTE
import ch.berta.fabio.verifitadmin.feature.clients.clients
import ch.berta.fabio.verifitadmin.feature.clients.navigateToClients
import ch.berta.fabio.verifitadmin.feature.login.LOGIN_ROUTE
import ch.berta.fabio.verifitadmin.feature.login.login
import ch.berta.fabio.verifitadmin.feature.login.navigateToLogin
import ch.berta.fabio.verifitadmin.feature.trainings.TRAININGS_ROUTE
import ch.berta.fabio.verifitadmin.feature.trainings.navigateToTrainings
import ch.berta.fabio.verifitadmin.feature.trainings.trainings
import ch.berta.fabio.verifitadmin.ui.theme.Theme
import kotlinx.coroutines.flow.filter

@Composable
fun App() {
    Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
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
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = {
            Text(
                text = "veri-fit Admin",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
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
            selected = currentDestination.isRouteInHierarchy(TRAININGS_ROUTE),
            onClick = { navController.navigateToTrainings() })
        NavigationBarItem(
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = null) },
            label = { Text(stringResource(R.string.clients)) },
            selected = currentDestination.isRouteInHierarchy(CLIENTS_ROUTE),
            onClick = { navController.navigateToClients() })
    }
}

private fun NavDestination?.isRouteInHierarchy(route: String): Boolean =
    this?.hierarchy?.any { it.route == route } ?: false

@Composable
private fun AppNavHost(
    navController: NavHostController,
    authState: AuthState,
    modifier: Modifier = Modifier
) {
    val isLoggedOut = authState is AuthState.LoggedOut
    val startDestination = if (isLoggedOut) LOGIN_ROUTE else TRAININGS_ROUTE
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        login()
        trainings()
        clients()
    }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow
            .filter { isLoggedOut && it.destination.route != LOGIN_ROUTE }
            .collect {
                navController.navigateToLogin()
            }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    App()
}