package org.brightmindenrichment.street_care.ui.home.start_now.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import org.brightmindenrichment.street_care.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BeforeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var letSomeoneKnowButton: ImageView
    private lateinit var letSomeoneKnowExpandableLayout: LinearLayout
    private lateinit var letSomeoneKnowCardView: CardView
    private lateinit var howToPrepareButton: ImageView
    private lateinit var howToPrepareExpandableLayout: LinearLayout
    private lateinit var howToPrepareCardView: CardView
    private lateinit var mustCarryButton: ImageView
    private lateinit var mustCarryExpandableLayout: LinearLayout
    private lateinit var mustCarryCardView: CardView
    private lateinit var planAnIntroButton: ImageView
    private lateinit var planAnIntroExpandableLayout: LinearLayout
    private lateinit var planAnIntroCardView: CardView

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
        return inflater.inflate(R.layout.fragment_before, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        letSomeoneKnowButton = view.findViewById(R.id.image_let_someone_know)
        letSomeoneKnowCardView = view.findViewById(R.id.cardView_let_someone_know)
        letSomeoneKnowExpandableLayout = view.findViewById(R.id.linearlayout_let_someone_know)

        howToPrepareButton = view.findViewById(R.id.image_how_to_prepare)
        howToPrepareCardView = view.findViewById(R.id.cardView_how_to_prepare)
        howToPrepareExpandableLayout = view.findViewById(R.id.linearlayout_how_to_prepare)

        mustCarryButton = view.findViewById(R.id.image_must_carry)
        mustCarryCardView = view.findViewById(R.id.cardView_must_carry)
        mustCarryExpandableLayout = view.findViewById(R.id.linearlayout_must_carry)

        planAnIntroButton = view.findViewById(R.id.image_plan_an_intro)
        planAnIntroCardView = view.findViewById(R.id.cardView_plan_an_intro)
        planAnIntroExpandableLayout = view.findViewById(R.id.linearlayout_plan_an_intro)


        letSomeoneKnowButton.setOnClickListener{
            showLetSomeoneKnowDetails()
        }

        howToPrepareButton.setOnClickListener {
            showHowToPrepareDetails()
        }

        mustCarryButton.setOnClickListener {
            showMustCarryDetails()
        }

        planAnIntroButton.setOnClickListener {
            showPlanAnIntroDetails()
        }

    }



    private fun showLetSomeoneKnowDetails() {
        if (letSomeoneKnowExpandableLayout.visibility == View.GONE) {
            TransitionManager.beginDelayedTransition(letSomeoneKnowCardView, AutoTransition())
            letSomeoneKnowExpandableLayout.visibility = View.VISIBLE
            letSomeoneKnowButton.setImageResource(R.drawable.ic_baseline_do_not_disturb_on_24)

        } else {
            TransitionManager.beginDelayedTransition(letSomeoneKnowCardView, AutoTransition())
            letSomeoneKnowExpandableLayout.visibility = View.GONE
            letSomeoneKnowButton.setImageResource(R.drawable.ic_baseline_add_circle_24)

        }
    }

    private fun showHowToPrepareDetails() {
        if(howToPrepareExpandableLayout.visibility == View.GONE){
            TransitionManager.beginDelayedTransition(howToPrepareCardView, AutoTransition())
            howToPrepareExpandableLayout.visibility = View.VISIBLE
            howToPrepareButton.setImageResource(R.drawable.ic_baseline_do_not_disturb_on_24)

        }else{
            TransitionManager.beginDelayedTransition(howToPrepareCardView, AutoTransition())
            howToPrepareExpandableLayout.visibility = View.GONE
            howToPrepareButton.setImageResource(R.drawable.ic_baseline_add_circle_24)
        }

    }

    private fun showMustCarryDetails() {
        if(mustCarryExpandableLayout.visibility == View.GONE){
            TransitionManager.beginDelayedTransition(mustCarryCardView, AutoTransition())
            mustCarryExpandableLayout.visibility = View.VISIBLE
            mustCarryButton.setImageResource(R.drawable.ic_baseline_do_not_disturb_on_24)
        }else{
            TransitionManager.beginDelayedTransition(mustCarryCardView, AutoTransition())
            mustCarryExpandableLayout.visibility = View.GONE
            mustCarryButton.setImageResource(R.drawable.ic_baseline_add_circle_24)
        }

    }
    private fun showPlanAnIntroDetails() {
        if(planAnIntroExpandableLayout.visibility == View.GONE){
            TransitionManager.beginDelayedTransition(planAnIntroCardView, AutoTransition())
            planAnIntroExpandableLayout.visibility = View.VISIBLE
            planAnIntroButton.setImageResource(R.drawable.ic_baseline_do_not_disturb_on_24)
        }else{
            TransitionManager.beginDelayedTransition(planAnIntroCardView, AutoTransition())
            planAnIntroExpandableLayout.visibility = View.GONE
            planAnIntroButton.setImageResource(R.drawable.ic_baseline_add_circle_24)
        }

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
}