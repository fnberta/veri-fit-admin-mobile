package ch.berta.fabio.verifitadmin.component.auth

fun interface SignOut {
    suspend operator fun invoke()
}
