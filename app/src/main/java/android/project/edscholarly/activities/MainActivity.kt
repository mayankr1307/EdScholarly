package android.project.edscholarly.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.project.edscholarly.R
import android.project.edscholarly.adapters.StudentQueryAdapter
import android.project.edscholarly.databinding.ActivityMainBinding
import android.project.edscholarly.firebase.FirebaseAuthHelper
import android.project.edscholarly.firebase.FirestoreHelper
import android.project.edscholarly.models.StudentQuery
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var mStudentQueryList = ArrayList<StudentQuery>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.navView?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    true
                }
                R.id.nav_sign_out -> {
                    FirebaseAuthHelper().signOutUser(this@MainActivity)
                    true
                }
                else -> false
            }
        }

        setUpUserInformation()

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val currentUserEmail = FirebaseAuthHelper().currentUserEmail()
        Log.d("MainActivity", "Retrieving student queries for email: $currentUserEmail")

        FirestoreHelper().retrieveStudentQueries(currentUserEmail) { studentQueries ->
            Log.d("MainActivity", "Student queries retrieved: ${studentQueries.size}")

            mStudentQueryList.clear()
            mStudentQueryList.addAll(studentQueries)

            if (studentQueries.isEmpty()) {
                Log.d("MainActivity", "No student queries found for the user.") //
            } else {
                Log.d("MainActivity", "Student Queries: $studentQueries")
            }

            val adapter = StudentQueryAdapter(this@MainActivity, mStudentQueryList)
            binding?.rvQueries?.layoutManager = LinearLayoutManager(this@MainActivity)
            binding?.rvQueries?.adapter = adapter
        }
    }


    private fun setUpUserInformation() {
        binding?.navView?.getHeaderView(0)?.findViewById<TextView>(R.id.user_name)?.text = FirebaseAuthHelper().currentUserName()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if(binding?.dlMain?.isDrawerOpen(GravityCompat.START) == false) binding?.dlMain?.openDrawer(GravityCompat.START)
        else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    fun signOutSuccess() {
        startActivity(Intent(this@MainActivity, IntroActivity::class.java))
        finish()
    }

    fun viewFullMessage(query: StudentQuery) {
        val intent = Intent(this@MainActivity, ViewQueryActivity::class.java)
        intent.putExtra("STUDENT_QUERY", query)
        startActivity(intent)
    }
}