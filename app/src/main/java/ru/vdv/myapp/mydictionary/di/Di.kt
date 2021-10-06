package ru.vdv.myapp.mydictionary.di

import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ru.vdv.myapp.mydictionary.model.data.AppState
import ru.vdv.myapp.mydictionary.model.data.DataModelFD
import ru.vdv.myapp.mydictionary.model.datasource.DataSourceRemote
import ru.vdv.myapp.mydictionary.model.datasource.RetrofitImplementation
import ru.vdv.myapp.mydictionary.model.repository.Repository
import ru.vdv.myapp.mydictionary.model.repository.RepositoryImpl
import ru.vdv.myapp.mydictionary.presenter.Interactor
import ru.vdv.myapp.mydictionary.view.main.MainInteractor
import ru.vdv.myapp.mydictionary.view.main.MainViewModel

object Di {
    private const val NAME_REPO_REMOTE = "Remote repository"


    val appModule = module {
        single<Repository<List<DataModelFD>>>(qualifier = named(name = NAME_REPO_REMOTE)) {
            RepositoryImpl(DataSourceRemote(RetrofitImplementation()))
        }
        single{ MainViewModel(get())}

    }

    val mainScreen = module {
        factory {
            Log.d("Koin", "Хобана... Сработал!")
            MainInteractor(
                remoteRepository = get(qualifier = named(name = NAME_REPO_REMOTE)),
                context = androidContext()
            )
        }
        //factory { MainViewModel(get()) }

    }

    val allModules = appModule + mainScreen
}
