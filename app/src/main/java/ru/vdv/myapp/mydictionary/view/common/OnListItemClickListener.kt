package ru.vdv.myapp.mydictionary.view.common

import ru.vdv.myapp.mydictionary.model.data.DataModelFD

interface OnListItemClickListener {
    fun onItemClick(data: DataModelFD)
}