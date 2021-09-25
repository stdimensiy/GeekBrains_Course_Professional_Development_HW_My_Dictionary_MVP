package ru.vdv.myapp.mydictionary.view.main

import io.reactivex.rxjava3.core.Observable
import ru.vdv.myapp.mydictionary.model.data.AppState
import ru.vdv.myapp.mydictionary.model.data.DataModelFD
import ru.vdv.myapp.mydictionary.model.repository.Repository
import ru.vdv.myapp.mydictionary.presenter.Interactor

class MainInteractor(
    private val remoteRepository: Repository<List<DataModelFD>>
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        //будем мапить результат: переводим резульирующие данные из объекта уровня
        // модели в объект уровня представления
        return remoteRepository.getData(word).map { AppState.Success(it) }
    }
}