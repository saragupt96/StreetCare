package org.brightmindenrichment.street_care.ui.community

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.brightmindenrichment.street_care.R
import java.text.SimpleDateFormat


class PendingEventsRecyclerAdapter(private val controller: pendingEventAdapter)
    : RecyclerView.Adapter<PendingEventsRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val pendingEventList: View) : RecyclerView.ViewHolder(pendingEventList) {

        val txtTitle: TextView = pendingEventList.findViewById(R.id.txtEventTitle)
        val txtDate: TextView = pendingEventList.findViewById(R.id.txtEventDate)
        val txtLocation: TextView = pendingEventList.findViewById(R.id.txtEventLocation)
        val txtDescription: TextView = pendingEventList.findViewById(R.id.txtEventDesc)
        val txtTime: TextView = pendingEventList.findViewById(R.id.txtEventTime)
        val btnApproved: Button = pendingEventList.findViewById(R.id.btnApproved)
        val btnDecline:Button= pendingEventList.findViewById(R.id.btnDecline)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pendingeventlist, parent, false)
        return  ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val event = controller.getEventAtPosition(position)
        if (event != null) {
            val eventId= event.eventId
            holder.txtTitle.text = event.title
            holder.txtDescription.text = event.description
            holder.txtLocation.text = event.location
            holder.txtDate.text = event.date
            holder.txtTime.text = event.time
            holder.btnApproved.setOnClickListener{
            if (eventId != null) {
                    controller.updateEventList(eventId)
                    controller.refresh {  }
                }
                else
                    Log.d("BME", "Failed UpdateEventList")


            }
            holder.btnDecline.setOnClickListener{

                if (eventId != null) {
                    controller.declineEventList(eventId)
                }
                else
                    Log.d("BME", "Failed DeclineEventList")

            }
        }
    }
    override fun getItemCount(): Int {
        return controller.size
    }
}


