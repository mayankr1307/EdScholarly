package android.project.edscholarly.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.project.edscholarly.R

@SuppressLint("CustomSplashScreen")
class SplashActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        object: CountDownTimer(3000, 3000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
                finish()
            }

        }.start()
    }
}