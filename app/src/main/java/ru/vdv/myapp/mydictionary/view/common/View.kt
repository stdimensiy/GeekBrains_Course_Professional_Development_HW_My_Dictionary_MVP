package ru.vdv.myapp.mydictionary.view.common

import ru.vdv.myapp.mydictionary.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}