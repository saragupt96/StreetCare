package org.brightmindenrichment.street_care.ui.community

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.brightmindenrichment.street_care.R



class CommunityFragment : Fragment() {
    private lateinit var buttonAdd: ImageButton

    private val eventDataAdapter = EventDataAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
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
        } else {
            buttonAdd = ImageButton(this.context)
            buttonAdd.setBackgroundResource(R.drawable.ic_menu_add)
            val l3 = Toolbar.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT,
                Toolbar.LayoutParams.WRAP_CONTENT
            )
            l3.gravity = Gravity.LEFT
            buttonAdd.layoutParams = l3
            toolbar.addView(buttonAdd)
            buttonAdd.setOnClickListener {
                findNavController().navigate(R.id.nav_add_event)
                Log.d("BME", "Add")
                onDetach()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        if (toolbar == null) {
            Log.d("BME", "Did not find toolbar")
        } else {
             buttonAdd.visibility=View.GONE
        }
    }
}// end class