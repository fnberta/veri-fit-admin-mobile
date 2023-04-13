package ch.berta.fabio.verifitadmin.core.auth.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object FirebaseAuthModule {

    @Provides fun providesFirebaseAuth(): FirebaseAuth = Firebase.auth
}
