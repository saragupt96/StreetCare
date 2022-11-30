package org.brightmindenrichment.street_care.YouTube

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.CoroutineExceptionHandler
import org.brightmindenrichment.street_care.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_VIDEO_ID = "videoId"

/**
 * A simple [Fragment] subclass.
 * Use the [YouTubeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class YouTubeListFragment : Fragment() {

    private var paramVideoId: String? = null
    var controller = YouTubeController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramVideoId = it.getString(ARG_VIDEO_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_you_tube_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("BME", "Got video Id ${paramVideoId}")

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(requireContext()).setTitle("Error...")
                .setMessage(exception.message)
                .setPositiveButton("Ok") { _, _ -> }
                .setIcon(R.drawable.donate_icon)
                .show()
        }


        if (paramVideoId != null) {
            controller.refresh(paramVideoId!!, errorHandler) {

                var items = controller.playlist?.items

                if (items != null) {
                    for (i in items) {
                        Log.d("BME", i.id)
                    }
                }
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment YouTubeListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            YouTubeListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_VIDEO_ID, paramVideoId)
                }
            }
    }
}