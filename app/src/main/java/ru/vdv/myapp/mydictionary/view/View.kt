package ru.vdv.myapp.mydictionary.view

import ru.vdv.myapp.mydictionary.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}