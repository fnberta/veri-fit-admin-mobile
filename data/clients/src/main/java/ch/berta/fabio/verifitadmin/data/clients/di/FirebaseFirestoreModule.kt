package ch.berta.fabio.verifitadmin.data.clients.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object FirebaseFirestoreModule {

    @Provides fun providesFirestore(): FirebaseFirestore = Firebase.firestore
}
