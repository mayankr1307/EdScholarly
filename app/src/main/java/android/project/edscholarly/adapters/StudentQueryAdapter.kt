package android.project.edscholarly.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.project.edscholarly.R
import android.project.edscholarly.activities.MainActivity
import android.project.edscholarly.activities.ViewQueryActivity
import android.project.edscholarly.models.StudentQuery

class StudentQueryAdapter(
    private val context: Context,
    private val studentQueries: List<StudentQuery>
) : RecyclerView.Adapter<StudentQueryAdapter.StudentQueryViewHolder>() {

    class StudentQueryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStudentEmail: TextView = itemView.findViewById(R.id.tv_student_email)
        val tvTeacherEmail: TextView = itemView.findViewById(R.id.tv_teacher_email)
        val tvQueryDate: TextView = itemView.findViewById(R.id.tv_query_date)
        val tvQueryTime: TextView = itemView.findViewById(R.id.tv_query_time)
        val tvQueryMessage: TextView = itemView.findViewById(R.id.tv_query_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentQueryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student_query, parent, false)
        return StudentQueryViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StudentQueryViewHolder, position: Int) {
        val query = studentQueries[position]
        holder.tvStudentEmail.text = query.studentEmail
        holder.tvTeacherEmail.text = query.teacherEmail
        holder.tvQueryDate.text = query.queryDate
        holder.tvQueryTime.text = query.queryTime

        holder.tvQueryMessage.text = query.queryMessage.take(20) + "..."

        holder.tvQueryMessage.setOnClickListener {
            if(context is MainActivity) {
                context.viewFullMessage(query)
            }
        }
    }

    override fun getItemCount(): Int {
        return studentQueries.size
    }
}
