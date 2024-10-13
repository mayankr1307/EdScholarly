package android.project.edscholarly.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.project.edscholarly.R
import android.project.edscholarly.databinding.ActivityIntroBinding
import android.project.edscholarly.firebase.FirebaseAuthHelper

class IntroActivity : AppCompatActivity() {

    private var binding: ActivityIntroBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        FirebaseAuthHelper().autoSignIn(this@IntroActivity)

        binding?.btnSignUp?.setOnClickListener {
            startActivity(Intent(this@IntroActivity, SignUpActivity::class.java))
        }

        binding?.btnSignIn?.setOnClickListener {
            startActivity(Intent(this@IntroActivity, SignInActivity::class.java))
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    fun autoSignInSuccess() {
        startActivity(Intent(this@IntroActivity, MainActivity::class.java))
    }
}