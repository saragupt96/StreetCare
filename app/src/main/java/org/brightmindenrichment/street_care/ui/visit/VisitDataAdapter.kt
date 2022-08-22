package org.brightmindenrichment.street_care.ui.visit

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.brightmindenrichment.street_care.ui.community.Event

class VisitDataAdapter {

    var visits: MutableList<VisitLog> = mutableListOf()

    val size: Int get() { return visits.size }


    /***
     * Returns a visit log for a given position.
     *
     * If the position is not valid, null is returned.
     * */
    fun getVisitAtPosition(position: Int): VisitLog? {

        if ((position >=0) && (position < visits.size)) {
            return visits[position]
        }

        return null
    }


    /**
     *
    Example:
        var adapter = VisitDataAdapter()

        adapter.refresh {
            for (visit in adapter.visits) {
                Log.d("BME", "${visit.location} ${visit.comments}")
            }
        }
     * */
    fun refresh(onComplete: () -> Unit) {

        // make sure somebody is logged in
        val user = Firebase.auth.currentUser ?: return

        val db = Firebase.firestore

        db.collection("surveys").get().addOnSuccessListener { result ->

            // we are going to reload the whole list, remove anything already cached
            this.visits.clear()

            for (document in result) {
                var visit = VisitLog()

                visit.location = document.get("location").toString()
                visit.hours = document.get("hoursSpentOnOutreach") as Long?
                visit.visitAgain = document.get("willPerformOutreachAgain").toString()
                visit.peopleCount = document.get("helpers") as Long?
                visit.experience = document.get("rating").toString()
                visit.comments = document.get("comments").toString()

                if (document.get("date") != null) {
                    val dt = document.get("date") as com.google.firebase.Timestamp
                    if (dt != null) {
                        visit.date = dt.toDate()
                    }
                }

                this.visits.add(visit)
            }


            onComplete()

        }
    }
} // end class