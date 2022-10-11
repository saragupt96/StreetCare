package org.brightmindenrichment.street_care.ui.home.what_to_give

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.brightmindenrichment.street_care.R
import org.brightmindenrichment.street_care.ui.home.data.ItemsToGive
import org.brightmindenrichment.street_care.ui.home.what_to_give.ItemToGiveAdapter.ItemsViewHolder

class ItemToGiveAdapter(private val context: Context, private val dataset :List<ItemsToGive>) : RecyclerView.Adapter<ItemsViewHolder>(){

     class ItemsViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val imageView : ImageView = view.findViewById(R.id.image_view_what_to_give)
         val textView : TextView = view.findViewById(R.id.item_title_what_to_give)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val itemLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_to_give_list,parent,false)
        return ItemsViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}