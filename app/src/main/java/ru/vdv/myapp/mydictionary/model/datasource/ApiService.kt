package ru.vdv.myapp.mydictionary.model.datasource

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ru.vdv.myapp.mydictionary.model.data.DataModelFD

interface ApiService {
    @GET("ru/{word}")
    fun search(
        @Path("word") wordToSearch: String
    ): Observable<List<DataModelFD>>
}