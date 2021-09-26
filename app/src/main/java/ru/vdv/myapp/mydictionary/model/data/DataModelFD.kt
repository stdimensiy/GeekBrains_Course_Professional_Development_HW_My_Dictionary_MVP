package ru.vdv.myapp.mydictionary.model.data

import com.google.gson.annotations.SerializedName

/**
 * Класс DataModelFD - это модель данных, получаемых от **[Free Dictionary API](https://dictionaryapi.dev/)**
 *
 * * **[word]**      - словарное слово (словосочетание) возвращенное сервером
 * * **[phonetic]**  - строка содержащая набор символов согласно Международного фонетического
 * алфавита определяюих произношение словарного слова (словосочетания)
 * * **[phonetics]** - массив наборов фонетической информации к данному словарному слову
 * (символьное представление произношения и (или) URL на ресурс содержащий аудиозапись в формате mp3)
 * * **[origin]**    - происхождение слова (словосочетания)
 * * **[meanings]**  - массив значений слов
 * @constructor     создает объект, содержащий информацию о словарном слове
 */

data class DataModelFD(
    @SerializedName("word")
    val word: String,
    @SerializedName("phonetic")
    val phonetic: String,
    @SerializedName("phonetics")
    val phonetics: List<Phonetics>,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("meanings")
    val meanings: List<Meanings>
)
