package org.brightmindenrichment.street_care.ui.community

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineExceptionHandler
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

    private lateinit var buttonAdd: ImageButton

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


        if (Firebase.auth.currentUser != null) {
            updateUI()
        }
        else {
            // TODO : some message to user
            Log.d("BME", "not logged in")
        }

    }



    private fun updateUI() {

        eventDataAdapter.refresh {

            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerCommunity)

            recyclerView?.layoutManager = LinearLayoutManager(view?.context)
            recyclerView?.adapter = CommunityRecyclerAdapter(eventDataAdapter)
        }
    }



    override fun onResume() {
        super.onResume()
        Log.d("BME", "onResume")

        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        if (toolbar == null) {
            Log.d("BME", "Did not find toolbar")
        }
        else {
            buttonAdd = ImageButton(this.context)
            buttonAdd.setBackgroundResource(R.drawable.ic_menu_add)
            val l3 = Toolbar.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT,
                Toolbar.LayoutParams.WRAP_CONTENT
            )
            l3.gravity = Gravity.RIGHT
            buttonAdd.layoutParams = l3
            toolbar.addView(buttonAdd)
        }
    }


    override fun onDetach() {
        super.onDetach()

        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        if (toolbar == null) {
            Log.d("BME", "Did not find toolbar")
        }
        else {
            if (buttonAdd != null) {
                toolbar.removeView(buttonAdd)
            }
        }
    }



    private fun createErrorHandler() : CoroutineExceptionHandler? {

        val act = activity
        if (act != null) {
            return CoroutineExceptionHandler { _, exception ->
                AlertDialog.Builder(act).setTitle("Error...")
                    .setMessage(exception.message)
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        }

        return null
    }
} // end class