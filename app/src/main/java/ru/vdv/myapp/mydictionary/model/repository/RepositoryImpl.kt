package ru.vdv.myapp.mydictionary.model.repository

import ru.vdv.myapp.mydictionary.model.data.DataModelFD
import ru.vdv.myapp.mydictionary.model.datasource.DataSource

class RepositoryImpl(
    private val dataSource: DataSource<List<DataModelFD>>
) : Repository<List<DataModelFD>> {

    override fun getData(word: String) = dataSource.getData(word)

}