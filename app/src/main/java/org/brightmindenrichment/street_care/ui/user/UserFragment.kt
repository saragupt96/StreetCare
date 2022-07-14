package org.brightmindenrichment.street_care.ui.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.brightmindenrichment.street_care.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFragment : Fragment() {

    private var currentUser: FirebaseUser? = null

    private lateinit var buttonLogin: Button
    private lateinit var buttonSignUp: Button
    private lateinit var buttonRemoveAccount: Button
    private lateinit var buttonSignOut: Button
    private lateinit var textViewWelcome: TextView

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_user, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // grab the logged in user, if there is one
        currentUser = Firebase.auth.currentUser

        buttonLogin = view.findViewById<Button>(R.id.user_button_login)
        buttonSignUp = view.findViewById<Button>(R.id.user_button_sign_up)
        buttonRemoveAccount = view.findViewById<Button>(R.id.user_button_remove_account)
        buttonSignOut = view.findViewById<Button>(R.id.user_button_sign_out)
        textViewWelcome = view.findViewById<TextView>(R.id.textViewWelcome)


        // setup click listeners
        buttonLogin.setOnClickListener {
            buttonLoginOnClick()
        }

        buttonSignOut.setOnClickListener {
            buttonSignOutOnClick()
        }

        buttonSignUp.setOnClickListener {
            buttonSignUpOnClick()
        }


        buttonRemoveAccount.setOnClickListener {
            buttonRemoveAccountOnClick()
        }

        updateUI()
    }



    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!hidden) {
            updateUI()
        }
    }


    private fun buttonRemoveAccountOnClick() {

        currentUser?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                currentUser = null
                updateUI()
            }
        }
    }



    private fun buttonLoginOnClick() {
        findNavController().navigate(R.id.action_nav_user_to_nav_login)
    }


    private fun buttonSignUpOnClick() {
        findNavController().navigate(R.id.action_nav_user_to_nav_sign_up)
    }



    private fun buttonSignOutOnClick() {
        if (currentUser != null) {
            Firebase.auth.signOut()
            currentUser = null
        }

        updateUI()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



    fun updateUI() {

        if (currentUser != null) {
            textViewWelcome.text = currentUser!!.displayName + " " + getString(R.string.welcome)

            buttonLogin.visibility = View.GONE
            buttonSignUp.visibility = View.GONE
            buttonRemoveAccount.visibility = View.VISIBLE
            buttonSignOut.visibility = View.VISIBLE
        }
        else {
            textViewWelcome.text = getString(R.string.welcome)
            buttonLogin.visibility = View.VISIBLE
            buttonSignUp.visibility = View.VISIBLE
            buttonRemoveAccount.visibility = View.GONE
            buttonSignOut.visibility = View.GONE
        }
    }

} // end class