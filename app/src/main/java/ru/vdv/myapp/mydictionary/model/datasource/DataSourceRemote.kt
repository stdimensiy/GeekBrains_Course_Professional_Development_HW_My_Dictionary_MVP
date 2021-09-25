package ru.vdv.myapp.mydictionary.model.datasource

import ru.vdv.myapp.mydictionary.model.data.DataModelFD

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
) : DataSource<List<DataModelFD>> {

    override fun getData(word: String) = remoteProvider.getData(word)

}