package ru.vdv.myapp.mydictionary.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vdv.myapp.mydictionary.R
import ru.vdv.myapp.mydictionary.model.data.DataModelFD
import ru.vdv.myapp.mydictionary.presenter.DataPresenterRU
import ru.vdv.myapp.mydictionary.view.common.OnListItemClickListener

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    //на данном этапе передаю объекты класса принимающего слоя модели
    // при финальной реализации планируется использовать объект слоя презентера (даже если он будет абсолютно одинаковым)
    private var data: List<DataPresenterRU>
) :
    RecyclerView.Adapter<MainItemViewHolder>() {
    fun setData(data: List<DataPresenterRU>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainItemViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view, parent, false) as View
    )

    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener { openInNewWindow(data[position]) }
    }

    private fun openInNewWindow(listItemData: DataPresenterRU) {
        onListItemClickListener.onItemClick(listItemData)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}