package android.project.edscholarly.firebase

import android.project.edscholarly.models.StudentQuery
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import android.util.Log

class FirestoreHelper {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val TAG = "FirestoreHelper"

    fun retrieveStudentQueries(currentUserEmail: String, onComplete: (List<StudentQuery>) -> Unit) {
        val studentQueries = mutableListOf<StudentQuery>()
        Log.d(TAG, "Retrieving student queries for email: $currentUserEmail")

        firestore.collection("studentQueries")
            .whereEqualTo("teacherEmail", currentUserEmail)
            .get()
            .addOnSuccessListener { querySnapshot ->
                Log.d(TAG, "Query successful, documents found: ${querySnapshot.size()}")

                if (querySnapshot != null && !querySnapshot.isEmpty) {
                    for (document: QueryDocumentSnapshot in querySnapshot) {
                        val query = document.toObject(StudentQuery::class.java)
                        studentQueries.add(query)
                        Log.d(TAG, "Retrieved query: ${query.queryMessage}") // Log each retrieved query
                    }
                } else {
                    Log.d(TAG, "No queries found for email: $currentUserEmail")
                }
                onComplete(studentQueries)
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error retrieving queries: ${exception.message}")
                onComplete(emptyList())
            }
    }
}
