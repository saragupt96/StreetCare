package org.brightmindenrichment.street_care.ui.community

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.brightmindenrichment.street_care.R


class EventListDataAdapter(private val eventList: List<String>) : RecyclerView.Adapter<EventListDataAdapter.ViewHolder>()
{

    inner class ViewHolder(listViewItem: View): RecyclerView.ViewHolder(listViewItem)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val eventView = LayoutInflater.from(parent.context).inflate(R.layout.event_list_layout, parent, false)
        return ViewHolder(eventView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {

    }

    override fun getItemCount(): Int
    {
        return eventList.size
    }


}