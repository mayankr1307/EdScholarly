package android.project.edscholarly.firebase

import android.project.edscholarly.activities.IntroActivity
import android.project.edscholarly.activities.MainActivity
import android.project.edscholarly.activities.SignInActivity
import android.project.edscholarly.activities.SignUpActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class FirebaseAuthHelper {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUpUser(signUpActivity: SignUpActivity, name: String, email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { profileTask ->
                        if (profileTask.isSuccessful) {
                            Toast.makeText(signUpActivity, "Sign up successful", Toast.LENGTH_SHORT).show()
                            signUpActivity.signUpSuccess()
                        }
                    }
                } else {
                    Toast.makeText(signUpActivity, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signInUser(signInActivity: SignInActivity, email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(signInActivity, "Sign in successful", Toast.LENGTH_SHORT).show()
                    signInActivity.signInSuccess()

                } else {
                    Toast.makeText(signInActivity, "Sign in failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signOutUser(mainActivity: MainActivity) {
        firebaseAuth.signOut()
        Toast.makeText(mainActivity, "Signed out successfully", Toast.LENGTH_SHORT).show()
        mainActivity.signOutSuccess()
    }

    fun autoSignIn(introActivity: IntroActivity) {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            Toast.makeText(introActivity, "Welcome back, ${currentUser.displayName}", Toast.LENGTH_SHORT).show()
            introActivity.autoSignInSuccess()
        }
    }

    fun currentUserName(): String {
        val currentUser = firebaseAuth.currentUser
        return currentUser?.displayName ?: "User Name"
    }

    fun currentUserEmail(): String {
        val currentUser = firebaseAuth.currentUser
        return currentUser?.email ?: "email"
    }
}
