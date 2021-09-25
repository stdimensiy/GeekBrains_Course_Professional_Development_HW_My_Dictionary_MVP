package ru.vdv.myapp.mydictionary.model.data

sealed class AppState {
    data class Success(val data: List<DataModelFD>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}

