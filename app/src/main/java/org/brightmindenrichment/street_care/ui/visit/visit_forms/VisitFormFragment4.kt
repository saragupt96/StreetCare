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
import org.brightmindenrichment.street_care.ui.visit.data.VisitLog


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




        binding.submitLog.setOnClickListener{
            sharedVisitViewModel.visitLog.comments = getUserComments()
            sharedVisitViewModel.saveVisitLog()
            sharedVisitViewModel.visitLog = VisitLog()

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