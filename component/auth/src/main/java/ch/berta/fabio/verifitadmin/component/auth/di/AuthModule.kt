package ch.berta.fabio.verifitadmin.component.auth.di

import ch.berta.fabio.verifitadmin.component.auth.AuthClient
import ch.berta.fabio.verifitadmin.component.auth.DefaultLaunchSignIn
import ch.berta.fabio.verifitadmin.component.auth.DefaultObserveAuthState
import ch.berta.fabio.verifitadmin.component.auth.DefaultProcessSignIn
import ch.berta.fabio.verifitadmin.component.auth.FirebaseAuthClient
import ch.berta.fabio.verifitadmin.component.auth.LaunchSignIn
import ch.berta.fabio.verifitadmin.component.auth.ObserveAuthState
import ch.berta.fabio.verifitadmin.component.auth.ProcessSignIn
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface AuthModule {

    @Binds fun bindAuthClient(authClient: FirebaseAuthClient): AuthClient

    @Binds fun bindObserveAuthState(observeAuthState: DefaultObserveAuthState): ObserveAuthState

    @Binds fun bindLaunchSignIn(launchSignIn: DefaultLaunchSignIn): LaunchSignIn

    @Binds fun bindProcessSignIn(processSignIn: DefaultProcessSignIn): ProcessSignIn
}
