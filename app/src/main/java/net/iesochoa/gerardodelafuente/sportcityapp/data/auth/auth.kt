package net.iesochoa.gerardodelafuente.sportcityapp.data.auth

import com.google.firebase.auth.FirebaseAuth

//Clase que se conectar con firebase

class AuthRepository(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) {

    // Login con el mail y contraseña
    fun login(
        email: String,
        password: String,
        onResult: (isSuccess: Boolean, errorMessage: String?) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    val message =
                        task.exception?.message ?: "Error desconocido al iniciar la sesión"
                    onResult(false, message)
                }
            }
    }
    // Compruebo si hay alguien logueado ya

    fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    // Cierrar la  sesión
    fun logout() {
        firebaseAuth.signOut()
    }

    //REGISTROO
    fun registro(
        email: String,
        password: String,
        onResult: (isSuccess: Boolean, errorMessage: String?) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }

}
