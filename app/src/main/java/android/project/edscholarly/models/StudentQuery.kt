package android.project.edscholarly.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentQuery(
    val studentEmail: String = "",
    val teacherEmail: String = "",
    val queryDate: String = "",
    val queryTime: String = "",
    val queryMessage: String = ""
) : Parcelable
