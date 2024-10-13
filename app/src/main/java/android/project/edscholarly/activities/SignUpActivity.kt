package android.project.edscholarly.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.project.edscholarly.R
import android.project.edscholarly.databinding.ActivitySignUpBinding
import android.project.edscholarly.firebase.FirebaseAuthHelper
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {

    private var binding: ActivitySignUpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnSignUp?.setOnClickListener {
            if(!validateForm()) {
                return@setOnClickListener
            }

            val name = binding?.etName?.text.toString().trim()
            val email = binding?.etEmail?.text.toString().trim()
            val password = binding?.etPassword?.text.toString().trim()

            FirebaseAuthHelper().signUpUser(this@SignUpActivity, name, email, password)
        }
    }

    private fun validateForm(): Boolean {
        val name = binding?.etName?.text.toString().trim()
        val email = binding?.etEmail?.text.toString().trim()
        val password = binding?.etPassword?.text.toString().trim()

        return when {
            name.isEmpty() -> {
                showToast("Name cannot be empty")
                false
            }
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

    fun signUpSuccess() {
        startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
        finish()
    }
}