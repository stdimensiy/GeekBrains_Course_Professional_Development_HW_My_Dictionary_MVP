package ru.vdv.myapp.mydictionary.model.data

import com.google.gson.annotations.SerializedName

/**
 * Класс Definitions связан с [DataModelFD] - и является частью модели данных, получаемых от **[Free Dictionary API](https://dictionaryapi.dev/)**
 *
 * * **[definition]** - определение соответствующего словарного слова (словосочетания)
 * * **[example]**    - пример использования словарного слова (словосочетания) в тексте
 * * **[synonyms]**   - набор (массив) синонимов
 * * **[antonyms]**   - набор (массив) антонимов
 * @constructor     создает объект, содержащий определение, применение и доп. информацию к словарному слову
 */
data class Definitions(
    @SerializedName("definition") val definition: String,
    @SerializedName("example") val example: String,
    @SerializedName("synonyms") val synonyms: List<String>,
    @SerializedName("antonyms") val antonyms: List<String>
)
