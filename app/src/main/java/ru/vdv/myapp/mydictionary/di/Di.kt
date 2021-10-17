package ru.vdv.myapp.mydictionary.di

import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.vdv.myapp.mydictionary.model.data.DataModelFD
import ru.vdv.myapp.mydictionary.model.datasource.DataSourceRemote
import ru.vdv.myapp.mydictionary.model.datasource.RetrofitImplementation
import ru.vdv.myapp.mydictionary.model.repository.Repository
import ru.vdv.myapp.mydictionary.model.repository.RepositoryImpl
import ru.vdv.myapp.mydictionary.view.main.MainInteractor
import ru.vdv.myapp.mydictionary.view.main.MainViewModel

object Di {
    private const val NAME_REPO_REMOTE = "Remote repository"


    private val appModule = module {
        single<Repository<List<DataModelFD>>>(qualifier = named(name = NAME_REPO_REMOTE)) {
            RepositoryImpl(DataSourceRemote(RetrofitImplementation()))
        }
        single { MainViewModel() }

    }

    private val mainScreen = module {
        factory {
            Log.d("Koin", "Хобана... Сработал!")
            MainInteractor(
                remoteRepository = get(qualifier = named(name = NAME_REPO_REMOTE)),
                context = androidContext()
            )
        }
    }

    val allModules = appModule + mainScreen
}
