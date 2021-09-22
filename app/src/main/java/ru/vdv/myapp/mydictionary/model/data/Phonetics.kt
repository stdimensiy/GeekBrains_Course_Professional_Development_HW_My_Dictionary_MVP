package ru.vdv.myapp.mydictionary.model.data

import com.google.gson.annotations.SerializedName

/**
 * Класс Definitions связан с [DataModelFD] - и является частью модели данных, получаемых от **[Free Dictionary API](https://dictionaryapi.dev/)**
 *
 * * **[text]**  - строка содержащая набор символов согласно Международного фонетического
 * алфавита, определяюих произношение словарного слова (словосочетания)
 * * **[audio]** - ссылка (URL) на ресурс содержащий аудиозапись в формате mp3
 * @constructor  создает объект, содержащий фонетическую информацию к словарному слову (словосочетанию)
 */
data class Phonetics(
    @SerializedName("text")
    val text: String,
    @SerializedName("audio")
    val audio: String
)
