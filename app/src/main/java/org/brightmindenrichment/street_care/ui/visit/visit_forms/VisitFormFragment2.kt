package org.brightmindenrichment.street_care.ui.visit.visit_forms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.brightmindenrichment.street_care.R
import org.brightmindenrichment.street_care.databinding.FragmentVisitForm2Binding


/**
 * A simple [Fragment] subclass.
 * Use the [VisitFormFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class VisitFormFragment2 : Fragment() {
    private var _binding : FragmentVisitForm2Binding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVisitForm2Binding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGoToPage3.setOnClickListener{
            findNavController().navigate(R.id.action_visitFormFragment2_to_visitFormFragment3)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

