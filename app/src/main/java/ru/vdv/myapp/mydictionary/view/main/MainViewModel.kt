package ru.vdv.myapp.mydictionary.view.main

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.observers.DisposableObserver
import ru.vdv.myapp.mydictionary.model.data.AppState
import ru.vdv.myapp.mydictionary.model.datasource.DataSourceRemote
import ru.vdv.myapp.mydictionary.model.repository.RepositoryImpl
import ru.vdv.myapp.mydictionary.view.common.BaseViewModel
import kotlin.coroutines.coroutineContext

class MainViewModel(
    application: Application
//    private val context: Context,
//    private val interactor: MainInteractor = MainInteractor(
//        context,
//        RepositoryImpl(DataSourceRemote())
//    )
) : BaseViewModel<AppState>(application = application) {
    private val interactor: MainInteractor = MainInteractor(
        context,
        RepositoryImpl(DataSourceRemote())
    )
    private val tt = this
    private var appState: AppState? = null

    override fun getData(word: String): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, false)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .doOnSubscribe { liveDataForViewToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
        return super.getData(word)
    }


    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(t: AppState) {
                Log.d("Моя проверка", "Работат объект $tt")
                appState = t
                liveDataForViewToObserve.value = t
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
                // реализация данного метода в рамках ДЗ не предусмотрена
            }
        }
    }
}