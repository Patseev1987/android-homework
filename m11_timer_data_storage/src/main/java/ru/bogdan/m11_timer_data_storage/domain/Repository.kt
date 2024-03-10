package ru.bogdan.m11_timer_data_storage.domain

interface Repository {



    fun saveText(text: String)
    //— будет записывать значения и в SharedPreference, и в локальную переменную.

    fun clearText()
    //— будет очищать значение и в SharedPreference, и в локальной переменной.

    fun getText(): String
    //— будет доставать значение из источников: сначала попытается взять значение локальной переменной;
    // если оно null, то попытаемся взять значение из SharedPreference.

}