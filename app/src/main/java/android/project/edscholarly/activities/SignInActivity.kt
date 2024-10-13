package android.project.edscholarly.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.project.edscholarly.R
import android.project.edscholarly.databinding.ActivitySignInBinding
import android.project.edscholarly.firebase.FirebaseAuthHelper
import android.widget.Toast

class SignInActivity : AppCompatActivity() {

    private var binding: ActivitySignInBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnSignIn?.setOnClickListener {
            if (!validateForm()) {
                return@setOnClickListener
            }

            val email = binding?.etEmail?.text.toString().trim()
            val password = binding?.etPassword?.text.toString().trim()

            FirebaseAuthHelper().signInUser(this, email, password)
        }
    }

    private fun validateForm(): Boolean {
        val email = binding?.etEmail?.text.toString().trim()
        val password = binding?.etPassword?.text.toString().trim()

        return when {
            email.isEmpty() -> {
                showToast("Email cannot be empty")
                false
            }
            password.isEmpty() -> {
                showToast("Password cannot be empty")
                false
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    fun signInSuccess() {
        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        finish()
    }
}
