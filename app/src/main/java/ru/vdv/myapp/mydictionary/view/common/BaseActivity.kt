package ru.vdv.myapp.mydictionary.view.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.vdv.myapp.mydictionary.model.data.AppState
import ru.vdv.myapp.mydictionary.presenter.Presenter

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView(this)
        super.onStop()
    }
}