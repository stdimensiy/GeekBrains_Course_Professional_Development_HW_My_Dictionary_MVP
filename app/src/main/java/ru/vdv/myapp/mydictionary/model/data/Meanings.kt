package ru.vdv.myapp.mydictionary.model.data

import com.google.gson.annotations.SerializedName

/**
 * Класс Definitions связан с [DataModelFD] - и является частью модели данных, получаемых от **[Free Dictionary API](https://dictionaryapi.dev/)**
 *
 * * **[partOfSpeech]** - строковое представление части речи к которой принадлежит словарное слово
 * * **[definitions]**  - набор (массив) определений словарного слова (словосочетания)
 * @constructor  создает объект, содержащий значение (принадлежность к части речи и набор определений)
 * к словарному слову (словосочетанию)
 */
data class Meanings(
    @SerializedName("partOfSpeech")
    val partOfSpeech: String,
    @SerializedName("definitions")
    val definitions: List<Definitions>
)
