package org.brightmindenrichment.street_care.ui.visit.visit_forms

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import org.brightmindenrichment.street_care.R
import org.brightmindenrichment.street_care.databinding.FragmentVisitForm1Binding
import org.brightmindenrichment.street_care.util.Extensions
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


/*
 * A simple [Fragment] subclass.
 * Use the [VisitFormFragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class VisitFormFragment1 : Fragment() {
    private var _binding: FragmentVisitForm1Binding? = null
    private val binding get() = _binding!!
    private val myCalendar: Calendar = Calendar.getInstance()
    private val sharedVisitViewModel: VisitViewModel by activityViewModels()
    private var displayDateFormat: String = "MM/dd/yyyy"


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
        onViewStateRestored(savedInstanceState)


        binding.datePickerActions.setOnClickListener{
          myCalendar.time =  populateCalendarToSelectVisitDate()
        }

        binding.btnGoToPage2.setOnClickListener {

            if (!sharedVisitViewModel.validateLocation(binding.location.text.toString())) {
                Extensions.showDialog(requireContext(), "Please fill your location ", "Ok")
            } else if (!sharedVisitViewModel.validateDate(sharedVisitViewModel.visitLog.date)) {
                Extensions.showDialog(requireContext(), "Please fill your past visit date", "Ok")
            } else {
               val location = binding.location.text.toString()

                sharedVisitViewModel.visitLog.location = location


                findNavController().navigate(R.id.action_visitFormFragment1_to_visitFormFragment2)
            }

        }

    }




    private fun populateCalendarToSelectVisitDate() : Date{
        val date =
            OnDateSetListener { view, year, month, day ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, day)
                displayDate(Extensions.dateToString(myCalendar.time, displayDateFormat))
                //setting the user selected date into object
                sharedVisitViewModel.visitLog.date = myCalendar.time

            }

        context?.let { it1 ->
            DatePickerDialog(
                it1,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)


            ).show()

        }
        return myCalendar.time

    }


    private fun displayDate(dateString: String) {
        binding.datePickerActions.setText(dateString)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


