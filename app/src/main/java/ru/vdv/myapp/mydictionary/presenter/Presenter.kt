package ru.vdv.myapp.mydictionary.presenter

import ru.vdv.myapp.mydictionary.model.data.AppState
import ru.vdv.myapp.mydictionary.view.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}