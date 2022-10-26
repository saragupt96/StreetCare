package org.brightmindenrichment.street_care.ui.visit.repository

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.brightmindenrichment.street_care.ui.visit.data.VisitLog

class VisitLogRepositoryImp : VisitLogRepository {


    override fun saveVisitLog(visitLog: VisitLog) {
        // make sure somebody is logged in
        val user = Firebase.auth.currentUser ?: return

        Log.d("BME", user.uid)


        // create a map of event data so we can add to firebase
        val visitData = hashMapOf(
            "location" to visitLog.location,
            "hoursSpentOnOutreach" to visitLog.hours,
            "willPerformOutreachAgain" to visitLog.visitAgain,
            "helpers" to visitLog.peopleCount,
            "rating" to visitLog.experience,
            "date" to visitLog.date,
            "comments" to visitLog.comments,
            "uid" to user.uid
        )

        // save to firebase
        val db = Firebase.firestore
        db.collection("surveys").add(visitData).addOnSuccessListener { documentReference ->
            Log.d("BME", "Saved with id ${documentReference.id}")

            //onComplete()
        } .addOnFailureListener { exception ->
            Log.w("BMR", "Error in addEvent ${exception.toString()}")
            //onComplete()
        }
    }


}// end of class