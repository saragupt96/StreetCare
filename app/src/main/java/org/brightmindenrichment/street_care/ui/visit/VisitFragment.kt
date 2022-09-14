package org.brightmindenrichment.street_care.ui.visit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.brightmindenrichment.street_care.R
import org.brightmindenrichment.street_care.databinding.FragmentVisitBinding
import java.util.*

class VisitFragment : Fragment() {
    private var _binding : FragmentVisitBinding? = null
    val binding get() = _binding!!

    companion object {
        fun newInstance() = VisitFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVisitBinding.inflate(inflater, container, false)
        return _binding!!.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitVisitLog.setOnClickListener{
            findNavController().navigate(R.id.action_nav_visit_to_visitFormFragment1)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}