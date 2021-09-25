package ru.vdv.myapp.mydictionary.model.datasource

import io.reactivex.rxjava3.core.Observable
import ru.vdv.myapp.mydictionary.model.data.DataModelFD

interface DataSource<T> {
    fun getData(word: String): Observable<List<DataModelFD>>
}