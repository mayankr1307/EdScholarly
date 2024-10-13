package android.project.edscholarly.activities

import android.os.Bundle
import android.project.edscholarly.R
import android.project.edscholarly.models.StudentQuery
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewQueryActivity : AppCompatActivity() {

    private lateinit var tvStudentEmail: TextView
    private lateinit var tvTeacherEmail: TextView
    private lateinit var tvQueryDate: TextView
    private lateinit var tvQueryTime: TextView
    private lateinit var tvFullQueryMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_query)

        tvStudentEmail = findViewById(R.id.tv_student_email)
        tvTeacherEmail = findViewById(R.id.tv_teacher_email)
        tvQueryDate = findViewById(R.id.tv_query_date)
        tvQueryTime = findViewById(R.id.tv_query_time)
        tvFullQueryMessage = findViewById(R.id.tv_full_query_message)

        val query: StudentQuery? = intent.getParcelableExtra("STUDENT_QUERY")

        query?.let {
            tvStudentEmail.text = it.studentEmail
            tvTeacherEmail.text = it.teacherEmail
            tvQueryDate.text = it.queryDate
            tvQueryTime.text = it.queryTime
            tvFullQueryMessage.text = it.queryMessage
        }
    }
}
