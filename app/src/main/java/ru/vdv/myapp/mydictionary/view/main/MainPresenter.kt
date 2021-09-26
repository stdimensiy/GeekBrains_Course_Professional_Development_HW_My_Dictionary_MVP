package ru.vdv.myapp.mydictionary.view.main

import android.content.Context
import android.util.Log
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.observers.DisposableObserver
import ru.vdv.myapp.mydictionary.model.data.AppState
import ru.vdv.myapp.mydictionary.model.datasource.DataSourceRemote
import ru.vdv.myapp.mydictionary.model.repository.RepositoryImpl
import ru.vdv.myapp.mydictionary.presenter.Presenter
import ru.vdv.myapp.mydictionary.schedulers.MySchedulers
import ru.vdv.myapp.mydictionary.schedulers.MySchedulersImpl
import ru.vdv.myapp.mydictionary.view.common.View

class MainPresenter<T : AppState, V : View>(
    private val context: Context,
    private val interactor: MainInteractor = MainInteractor(context, RepositoryImpl(DataSourceRemote())),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val schedulers: MySchedulers = MySchedulersImpl()
) : Presenter<T, V> {
    private var currentView: V? = null

    override fun attachView(view: V) {
        Log.d("Моя проверка", "есть приаттачили вьюху")
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable += interactor
            .getData(word, isOnline)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
            .subscribeWith(getObserver())
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}