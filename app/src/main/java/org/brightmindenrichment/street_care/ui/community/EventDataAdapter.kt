package org.brightmindenrichment.street_care.ui.community

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp
import java.util.*

class EventDataAdapter {

    var events: MutableList<Event> = mutableListOf()


    fun addEvent(title: String, description: String, date: Date, onComplete: () -> Unit) {

        // make sure somebody is logged in
        val user = Firebase.auth.currentUser ?: return

        // create a map of event data so we can add to firebase
        val eventData = hashMapOf(
            "title" to title,
            "description" to description,
            "date" to date,
            "interest" to 1,
            "uid" to user.uid
        )

        // save to firebase
        val db = Firebase.firestore
        db.collection("events").add(eventData).addOnSuccessListener { documentReference ->
            Log.d("BME", "Saved with id ${documentReference.id}")
            onComplete()
        } .addOnFailureListener { exception ->
            Log.w("BMR", "Error in addEvent ${exception.toString()}")
            onComplete()
        }
    }



    fun setLikedEvent(eventId: String, doesLike: Boolean, onComplete: () -> Unit) {

        // make sure somebody is logged in
        val user = Firebase.auth.currentUser ?: return

        val db = Firebase.firestore
        if (doesLike) {  // add a record if liked

            // create a map of the data to add to firebase
            val likedData = hashMapOf(
                "uid" to user.uid,
                "eventId" to eventId
            )

            db.collection("likedEvents").document().set(likedData).addOnSuccessListener {
                Log.d("BME", "saved liked")
                onComplete()
            }
        }
        else {
            // delete record of the like of this event for this user
            db.collection("likedEvents").whereEqualTo("uid", user.uid).whereEqualTo("eventId", eventId).get().addOnSuccessListener { result ->
                for (document in result) {
                    db.collection("likedEvents").document(document.id).delete()
                }
                onComplete()
            }
                .addOnFailureListener { exception ->
                    Log.w("BME", "Failed to save liked event ${exception.toString()}")
                    onComplete()
                }
        }
    }


    fun refresh(onComplete: () -> Unit) {

        // make sure somebody is logged in
        val user = Firebase.auth.currentUser ?: return

        val db = Firebase.firestore

        db.collection("events").get().addOnSuccessListener { result ->

            this.events.clear()

            for (document in result) {
                var event = Event()

                event.eventId = document.id

                event.title = document.get("title").toString()
                event.description = document.get("description").toString()
                event.uid = document.get("uid").toString()

                val dt = document.get("date") as com.google.firebase.Timestamp
                if (dt != null) {
                    event.date = dt.toDate()
                }

                this.events.add(event)
            }

            refreshedLiked {
                onComplete()
            }
        }
    }


    private fun refreshedLiked(onComplete: () -> Unit) {

        // make sure somebody is logged in
        val user = Firebase.auth.currentUser ?: return

        val db = Firebase.firestore

        db.collection("likedEvents").whereEqualTo("uid", user.uid).get().addOnSuccessListener { results ->

            for (document in results) {
                for (event in this.events) {
                    if (event.eventId == document.get("eventId").toString()) {
                        event.liked = true
                    }
                }
            }

            onComplete()
        }
            .addOnFailureListener { exceptioon ->
                onComplete()
            }

    }


} // end class