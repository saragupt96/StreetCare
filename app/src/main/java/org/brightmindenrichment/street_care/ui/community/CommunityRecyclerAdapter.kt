package org.brightmindenrichment.street_care.ui.community

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import org.brightmindenrichment.street_care.R
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class CommunityRecyclerAdapter(private val controller: EventDataAdapter) : RecyclerView.Adapter<CommunityRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val communityItemView: View) : RecyclerView.ViewHolder(communityItemView) {

        val textViewTitle: TextView = communityItemView.findViewById<TextView>(R.id.textViewCommunityTitle)
        val textViewSmallDate: TextView = communityItemView.findViewById<TextView>(R.id.textViewSmallDate)
        val textViewFullDate: TextView = communityItemView.findViewById<TextView>(R.id.textViewFullDate)
        val textViewDescription: TextView = communityItemView.findViewById<TextView>(R.id.textViewCommunityDescription)

        init {

        }
    }


    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list_layout, parent, false)

        return  ViewHolder(view)
    }



    override fun getItemCount(): Int {
        return controller.size
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val event = controller.getEventAtPosition(position)

        if (event != null) {
            holder.textViewTitle.text = event.title.orEmpty()
            holder.textViewDescription.text = event.description.orEmpty()

            if (event.date != null) {
                holder.textViewSmallDate.text = SimpleDateFormat("MMM dd").format(event.date)
                holder.textViewFullDate.text = SimpleDateFormat("DD, MMM HH:mm").format(event.date)
            }
            else {
                holder.textViewSmallDate.text = ""
            }
        }
    }
}