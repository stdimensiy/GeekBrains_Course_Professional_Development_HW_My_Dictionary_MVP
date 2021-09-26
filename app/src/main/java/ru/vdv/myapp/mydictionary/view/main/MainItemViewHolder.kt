package ru.vdv.myapp.mydictionary.view.main

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.vdv.myapp.mydictionary.R
import ru.vdv.myapp.mydictionary.presenter.DataPresenterRU

class MainItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(data: DataPresenterRU) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            itemView.findViewById<TextView>(R.id.header_textview_recycler_item).text = data.word
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                itemView.findViewById<TextView>(R.id.description_textview_recycler_item).text =
                    Html.fromHtml(data.definitions, 0)
            } else {
                itemView.findViewById<TextView>(R.id.description_textview_recycler_item).text =
                    Html.fromHtml(data.definitions)
            }
        }
    }
}