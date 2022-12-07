package org.brightmindenrichment.street_care.ui.user

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.brightmindenrichment.street_care.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class   LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var auth: FirebaseAuth


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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val buttonLogin = view.findViewById<Button>(R.id.loginButton)

        buttonLogin.setOnClickListener {


            val textEmail = view.findViewById<EditText>(R.id.editTextTextEmailAddress)
            val textPassword = view.findViewById<EditText>(R.id.editTextTextPassword2)

            var email = textEmail.text.toString() ?: ""
            var password = textPassword.text.toString() ?: ""

            auth = Firebase.auth

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(activity, "Successfully Login", Toast.LENGTH_SHORT).show();
                           textEmail.text.clear()
                            textPassword.text.clear()
                            findNavController().navigate(R.id.nav_user)
                        } else {
                            Toast.makeText(activity, getString(R.string.error_login_failed), Toast.LENGTH_SHORT).show();
                        }
                    }

        }
        val buttonSignup = view.findViewById<Button>(R.id.buttonSignUp)
        buttonSignup.setOnClickListener {
            findNavController().navigate(R.id.nav_sign_up)
        }

        val txtForgot=view.findViewById<TextView>(R.id.txtforget)
        txtForgot.setOnClickListener {
            showRecoverPasswordDialog();


        }
    }

    private fun showRecoverPasswordDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Recover Password")

        val editText=EditText(context)
        editText.setHint("Enter Email")
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
        builder.setView(editText)
        editText.setPadding(10,10,10,10)
        builder.setPositiveButton("Submit", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var m_Text = editText.text.toString()
            beginRecovery(m_Text);
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()

    }

    private fun beginRecovery(mText: String) {
        auth = Firebase.auth
        auth.sendPasswordResetEmail(mText)
            .addOnCompleteListener(OnCompleteListener<Void?> { task ->

                if (task.isSuccessful) {
                    // if isSuccessful then done message will be shown
                    // and you can change the password
                    Toast.makeText(context, "Done sent", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Error Occurred", Toast.LENGTH_LONG).show()
                }
            }).addOnFailureListener(OnFailureListener {

                Toast.makeText(context, "Error Failed", Toast.LENGTH_LONG).show()
            })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}