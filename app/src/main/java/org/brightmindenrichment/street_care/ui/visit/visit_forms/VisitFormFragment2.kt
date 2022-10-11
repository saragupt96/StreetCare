package org.brightmindenrichment.street_care.ui.visit.visit_forms



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.slider.Slider
import org.brightmindenrichment.street_care.R
import org.brightmindenrichment.street_care.databinding.FragmentVisitForm2Binding
import org.brightmindenrichment.street_care.util.Extensions


/**
 * A simple [Fragment] subclass.
 * Use the [VisitFormFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class VisitFormFragment2 : Fragment() {
    private var _binding : FragmentVisitForm2Binding? = null
    val binding get() = _binding!!
    private val sharedVisitViewModel : VisitViewModel by activityViewModels()
    private var outreach : String? = null
    private var peopleCount = 0L
    private var click: Boolean = true

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

        binding.rangeSlider.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
            // setting the hourly spent time for outreach
            binding.tvHours.text = getString(R.string.hours_spent,Extensions.floatToLong(value))
            //hourSpentOnOutreach(Extensions.floatToLong(value))
            sharedVisitViewModel.setHours(Extensions.floatToLong(value))

        })
        // setting outreach options
        binding.btnYes.setOnClickListener{
            sharedVisitViewModel.setVisitAgain(getString(R.string.yes))

        }
        binding.btnNo.setOnClickListener{
            sharedVisitViewModel.setVisitAgain(getString(R.string.no))

        }
        binding.btnUndecided.setOnClickListener{
            sharedVisitViewModel.setVisitAgain(getString(R.string.undecided))

        }

        binding.increaseNoOfPeople.setOnClickListener{
        peopleCount = sharedVisitViewModel.increment()
            sharedVisitViewModel.setPeopleCount(peopleCount)
            //setText()
            binding.tvNoOfPeople.text = sharedVisitViewModel.peopleCount.value.toString()
        }

        binding.decreaseNoOfPeople.setOnClickListener{
            peopleCount = sharedVisitViewModel.decrement()
            sharedVisitViewModel.setPeopleCount(peopleCount)
            //setText()
            binding.tvNoOfPeople.text = sharedVisitViewModel.peopleCount.value.toString()
        }
        binding.btnGoToPage3.setOnClickListener{
            goToNextScreen()
        }

    }

    private fun hourSpentOnOutreach( spentHour : Long){
        sharedVisitViewModel.setHours(spentHour)
    }

    private fun goToNextScreen(){
        findNavController().navigate(R.id.action_visitFormFragment2_to_visitFormFragment3)
    }


    private fun setText(){

        binding.tvNoOfPeople.text = sharedVisitViewModel.peopleCount.value.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

