package ru.vdv.myapp.mydictionary.view.common

import ru.vdv.myapp.mydictionary.model.data.DataModelFD
import ru.vdv.myapp.mydictionary.presenter.DataPresenterRU

interface OnListItemClickListener {
    fun onItemClick(data: DataPresenterRU)
}