package ch.berta.fabio.verifitadmin.core.auth.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object GoogleOneTapModule {

    @Provides
    fun providesOneTapClient(@ApplicationContext context: Context): SignInClient =
        Identity.getSignInClient(context)
}