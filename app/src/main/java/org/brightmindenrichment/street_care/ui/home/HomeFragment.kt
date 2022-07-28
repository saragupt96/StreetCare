package org.brightmindenrichment.street_care.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.slider.Slider
import com.smarteist.autoimageslider.SliderView
import org.brightmindenrichment.street_care.R
import org.brightmindenrichment.street_care.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val images = arrayOf<Int>(R.drawable.image12,R.drawable.image16,R.drawable.image25)
    lateinit var sliderView:SliderView
    lateinit var cardStartNow: CardView
    lateinit var cardWhatToGive: CardView
    lateinit var cardHowToVideos: CardView
    var sliderAdapter: SliderAdapter = SliderAdapter(images)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSliderImagesView(view)
        cardStartNow = view.findViewById(R.id.card_start_now)
        cardWhatToGive = view.findViewById(R.id.card_what_to_give)
        cardHowToVideos = view.findViewById(R.id.card_how_to_videos)

        cardStartNow.setOnClickListener(){
            findNavController().navigate(R.id.action_nav_home_to_startNowFragment)
        }

        cardWhatToGive.setOnClickListener(){
            Toast.makeText(view.context,"What to Give called",Toast.LENGTH_SHORT).show()
        }

        cardHowToVideos.setOnClickListener(){
            Toast.makeText(view.context,"How To Videos called",Toast.LENGTH_SHORT).show()
        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setUpSliderImagesView(view : View){
        sliderView = view.findViewById(R.id.slider_view)
        sliderView.setSliderAdapter(sliderAdapter)
        sliderView.autoCycleDirection = Slider.LAYOUT_DIRECTION_LTR
        sliderView.isAutoCycle= true
        sliderView.scrollTimeInSec =2
        sliderView.startAutoCycle()
    }

}


