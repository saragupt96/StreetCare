package org.brightmindenrichment.street_care.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import org.brightmindenrichment.street_care.R
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPassword2: EditText
    private lateinit var editTextCompany: EditText
    private lateinit var buttonSignUp: Button

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
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextUsername = view.findViewById<EditText>(R.id.editTextSignUpUserName)
        editTextEmail = view.findViewById<EditText>(R.id.editTextSignUpEmail)
        editTextPassword = view.findViewById<EditText>(R.id.editTextSignUpPassword)
        editTextPassword2 = view.findViewById<EditText>(R.id.editTextSignUpReEnterPassword)
        editTextCompany = view.findViewById<EditText>(R.id.editTextSignUpCompany)
        buttonSignUp = view.findViewById(R.id.buttonSignUpSignUp)

        buttonSignUp.setOnClickListener {
            buttonSignUpOnClick()
        }
    }


    private fun buttonSignUpOnClick() {

        val userName = editTextUsername.text.toString()
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        val password2 = editTextPassword2.text.toString()
        val company = editTextCompany.text.toString()

        if (userName != "" && email != "" && password != "" && password2 != "") {
            if (password == password2) {

                Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        val currentUser = Firebase.auth.currentUser

                        val userData = hashMapOf<String, Any>(
                            "dateCreated" to Date(),
                            "deviceType" to "Android",
                            "email" to email ,
                            "isValid" to true,
                            "organization" to company,
                            "username" to userName,
                            "uid" to (currentUser?.uid ?: "??")
                        )

                        val db = FirebaseFirestore.getInstance()
                        db.collection("users").add(userData).addOnCompleteListener { task ->
                            Toast.makeText(activity, "Successfully Register", Toast.LENGTH_SHORT).show();
                            findNavController().navigateUp()
                            editTextCompany.text.clear()
                            editTextEmail.text.clear()
                            editTextPassword.text.clear()
                            editTextPassword2.text.clear()
                            editTextUsername.text.clear()
                        }
                    }
                    else {
                        Toast.makeText(activity, getString(R.string.error_failed_to_create_user), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(activity, getString(R.string.error_password_must_match), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(activity, getString(R.string.error_missing_required_data), Toast.LENGTH_SHORT).show();
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}