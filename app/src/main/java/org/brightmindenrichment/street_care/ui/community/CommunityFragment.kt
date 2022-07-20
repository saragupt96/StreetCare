package org.brightmindenrichment.street_care.ui.community

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.brightmindenrichment.street_care.R
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommunityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommunityFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    private lateinit var buttonTestFirestore: Button

    private val eventDataAdapter = EventDataAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonTestFirestore = view.findViewById<Button>(R.id.buttonTestFirestore)

        if (Firebase.auth.currentUser != null) {

            // example addEvent
            //eventDataAdapter.addEvent("Food for Androids", "Feed all the android of the world.", Date()) {
            //    Log.d("BME", "Event added")
            //}

            //example setLiked
            //eventDataAdapter.setLikedEvent("2r9Z5TKnQYFC96iMAB1i", true) {
            //    Log.d("BME", "done")
            //}

            // example refresh
            //eventDataAdapter.refresh {
            //    for (event in this.eventDataAdapter.events) {
            //        Log.d("BME", "${event.title} ${event.date} ${event.liked}")
            //    }
            //}
        }
        else {
            Log.d("BME", "not logged in")
        }

    }


} // end class