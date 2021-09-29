package ru.vdv.myapp.mydictionary.view.common

import androidx.appcompat.app.AppCompatActivity
import ru.vdv.myapp.mydictionary.model.data.AppState

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)
}