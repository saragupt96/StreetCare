package org.brightmindenrichment.street_care.ui.home.start_now.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import org.brightmindenrichment.street_care.R
import org.brightmindenrichment.street_care.databinding.CardBeforeFragmentBinding
import org.brightmindenrichment.street_care.databinding.FragmentBeforeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BeforeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val TAG : String = "Before Fragment"

    private var  _binding : FragmentBeforeBinding? = null
    private val binding get() = _binding!!


private lateinit var includedView : CardBeforeFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBeforeBinding.inflate(inflater, container, false)
         val view : View = binding.root
        includedView = binding.cardBeforeFragment

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       includedView.imageLetSomeoneKnow.setOnClickListener{
            showLetSomeoneKnowDetails()
        }

        includedView.imageHowToPrepare.setOnClickListener {
            showHowToPrepareDetails()
        }

        includedView.imageMustCarry.setOnClickListener {
            showMustCarryDetails()
        }

        includedView.imagePlanAnIntro.setOnClickListener {
            showPlanAnIntroDetails()
        }

    }



    private fun showLetSomeoneKnowDetails() {
        if (includedView.linearlayoutLetSomeoneKnow.visibility == View.GONE) {
            TransitionManager.beginDelayedTransition(includedView.cardViewLetSomeoneKnow, AutoTransition())
            includedView.linearlayoutLetSomeoneKnow.visibility = View.VISIBLE
            includedView.imageLetSomeoneKnow.setImageResource(R.drawable.ic_baseline_do_not_disturb_on_24)
            //fetchDataFromDatabase()

        } else {
            TransitionManager.beginDelayedTransition(includedView.cardViewLetSomeoneKnow, AutoTransition())
            includedView.linearlayoutLetSomeoneKnow.visibility = View.GONE
            includedView.imageLetSomeoneKnow.setImageResource(R.drawable.ic_baseline_add_circle_24)

        }
    }

    private fun showHowToPrepareDetails() {
        if(includedView.linearlayoutHowToPrepare.visibility == View.GONE){
            TransitionManager.beginDelayedTransition(includedView.cardViewHowToPrepare, AutoTransition())
            includedView.linearlayoutHowToPrepare.visibility = View.VISIBLE
            includedView.imageHowToPrepare.setImageResource(R.drawable.ic_baseline_do_not_disturb_on_24)

        }else{
            TransitionManager.beginDelayedTransition(includedView.cardViewHowToPrepare, AutoTransition())
            includedView.linearlayoutHowToPrepare.visibility = View.GONE
            includedView.imageHowToPrepare.setImageResource(R.drawable.ic_baseline_add_circle_24)
        }

    }

    private fun showMustCarryDetails() {
        if(includedView.linearlayoutMustCarry.visibility == View.GONE){
            TransitionManager.beginDelayedTransition(includedView.cardViewMustCarry, AutoTransition())
            includedView.linearlayoutMustCarry.visibility = View.VISIBLE
            includedView.imageMustCarry.setImageResource(R.drawable.ic_baseline_do_not_disturb_on_24)
        }else{
            TransitionManager.beginDelayedTransition(includedView.cardViewMustCarry, AutoTransition())
            includedView.linearlayoutMustCarry.visibility = View.GONE
            includedView.imageMustCarry.setImageResource(R.drawable.ic_baseline_add_circle_24)
        }

    }
    private fun showPlanAnIntroDetails() {
        if(includedView.linearlayoutPlanAnIntro.visibility == View.GONE){
            TransitionManager.beginDelayedTransition(includedView.cardViewPlanAnIntro, AutoTransition())
            includedView.linearlayoutPlanAnIntro.visibility = View.VISIBLE
            includedView.imagePlanAnIntro.setImageResource(R.drawable.ic_baseline_do_not_disturb_on_24)
        }else{
            TransitionManager.beginDelayedTransition(includedView.cardViewPlanAnIntro, AutoTransition())
            includedView.linearlayoutPlanAnIntro.visibility = View.GONE
            includedView.imagePlanAnIntro.setImageResource(R.drawable.ic_baseline_add_circle_24)
        }

    } // end of showPlanAnIntroDetails method


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BeforeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BeforeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
} //end of class