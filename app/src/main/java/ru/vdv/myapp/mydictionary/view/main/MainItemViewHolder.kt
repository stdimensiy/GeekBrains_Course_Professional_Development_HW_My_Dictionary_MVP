package ru.vdv.myapp.mydictionary.view.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.vdv.myapp.mydictionary.R
import ru.vdv.myapp.mydictionary.model.data.DataModelFD

class MainItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(data: DataModelFD) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            itemView.findViewById<TextView>(R.id.header_textview_recycler_item).text = data.word
            itemView.findViewById<TextView>(R.id.description_textview_recycler_item).text =
                data.meanings.get(0).definitions.get(0).definition
            //itemView.setOnClickListener { openInNewWindow(data) }
        }
    }

}