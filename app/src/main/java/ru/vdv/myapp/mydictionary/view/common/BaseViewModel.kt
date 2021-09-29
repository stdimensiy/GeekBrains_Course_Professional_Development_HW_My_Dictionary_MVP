package ru.vdv.myapp.mydictionary.view.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.vdv.myapp.mydictionary.model.data.AppState
import ru.vdv.myapp.mydictionary.rx.MySchedulers
import ru.vdv.myapp.mydictionary.rx.MySchedulersImpl

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulers: MySchedulers = MySchedulersImpl(),
    application: Application

) : AndroidViewModel(application) {
    protected val context by lazy { getApplication<Application>().applicationContext }

    open fun getData(word: String): LiveData<T> = liveDataForViewToObserve

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    abstract fun getData(word: String, isOnline: Boolean): LiveData<AppState>
}