package org.brightmindenrichment.street_care.util

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class Extensions {
    companion object{
        fun floatToLong (value : Float) : Long{
            return value.toLong()
        }

        fun dateToString(date: Date?, format: String): String {
            val dateFormat = SimpleDateFormat(format, Locale.US)
            return dateFormat.format(date)
        }

        fun showDialog(context : Context, message : String, textPositivebtn : String) {
            val builder = AlertDialog.Builder(context)
            builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(textPositivebtn, DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                }
                )
            val alert = builder.create()
            alert.setTitle("Alert!")
            alert.show()
        }


    }





    fun Context.createDialog(layout: Int, cancelable: Boolean, message : String): Dialog {
        val dialog = Dialog(this, R.style.Theme_Dialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layout)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(cancelable)
        return dialog
    }


}