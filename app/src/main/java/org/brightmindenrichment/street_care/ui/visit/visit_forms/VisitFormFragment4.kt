package org.brightmindenrichment.street_care.ui.visit.visit_forms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import org.brightmindenrichment.street_care.R
import org.brightmindenrichment.street_care.databinding.FragmentVisitForm4Binding


class VisitFormFragment4 : Fragment() {
    private var _binding : FragmentVisitForm4Binding? = null
    private val binding get() = _binding!!
    private val sharedVisitViewModel : VisitViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVisitForm4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.commentsEditText.setOnClickListener {
//            println("------------11--------------${view.id}")
//            println("------------12--------------${binding.commentsEditText.id}")
//            if (view.id == binding.commentsEditText.id) {
//                println("------------1--------------")
//                binding.commentsEditText.isFocused
//                comments = getUserComments()
//                println("comments  +++++++++++++++++${comments}")
//                sharedVisitViewModel.setComments(binding.commentsEditText.text.toString())
//            } else {
//                println("----------2----------------")
//                binding.commentsEditText.clearFocus()
//            }
//        }



        binding.submitLog.setOnClickListener{
            sharedVisitViewModel.setComments(getUserComments())
            sharedVisitViewModel.submitVisitLog()
            println("---------------------------------------------------------------------------")
            println("location from visitlog_____________${sharedVisitViewModel.location.value}")
            println("hours spent -----------------------${sharedVisitViewModel.hours.value}")
            println("outreach------------------${sharedVisitViewModel.visitAgain.value}")
            println("final people count_____________${sharedVisitViewModel.peopleCount.value}")
            println("outreach experience_____________${sharedVisitViewModel.experience.value}")
            println("comments from visitlog_____________${sharedVisitViewModel.comments.value}")

            findNavController().navigate(R.id.action_visitFormFragment4_to_surveySubmittedFragment)
        }
    }


    private fun getUserComments() : String{
        return binding.commentsEditText.text.toString()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}