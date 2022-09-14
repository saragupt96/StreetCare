package org.brightmindenrichment.street_care.ui.visit.visit_forms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.brightmindenrichment.street_care.R
import org.brightmindenrichment.street_care.databinding.FragmentVisitForm1Binding

/*
 * A simple [Fragment] subclass.
 * Use the [VisitFormFragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class VisitFormFragment1 : Fragment() {
    private var _binding: FragmentVisitForm1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVisitForm1Binding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGoToPage2.setOnClickListener{
            findNavController().navigate(R.id.action_visitFormFragment1_to_visitFormFragment2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}