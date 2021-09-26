package ru.vdv.myapp.mydictionary.model.data

import ru.vdv.myapp.mydictionary.presenter.DataPresenterRU

sealed class AppState {
    data class Success(val data: List<DataPresenterRU>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}

