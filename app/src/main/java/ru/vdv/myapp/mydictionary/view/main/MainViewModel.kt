package ru.vdv.myapp.mydictionary.view.main

import android.app.Application
import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.observers.DisposableObserver
import ru.vdv.myapp.mydictionary.model.data.AppState
import ru.vdv.myapp.mydictionary.model.datasource.DataSourceRemote
import ru.vdv.myapp.mydictionary.model.repository.RepositoryImpl
import ru.vdv.myapp.mydictionary.view.common.BaseViewModel

class MainViewModel(
    application: Application
) : BaseViewModel<AppState>(application = application) {
    private val interactor: MainInteractor = MainInteractor(
        context,
        RepositoryImpl(DataSourceRemote())
    )
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