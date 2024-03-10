package ru.bogdan.m11_timer_data_storage.data

import android.content.Context
import ru.bogdan.m11_timer_data_storage.domain.Repository


class RepositoryImpl(private val context: Context) : Repository {

    private var _localVariable: String? = null
    private val localVariable get() = _localVariable

    private val sharedPreferences by lazy {
        context.getSharedPreferences(NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE)
    }

    private val editor by lazy {
        sharedPreferences.edit()
    }

    private fun getDataFromSharedPreference(): String? {
        return sharedPreferences.getString(SHARED_KEY, null)
    }

    private fun getDataFromLocalVariable(): String? {
        return localVariable
    }

    override fun saveText(text: String) {
        _localVariable = text
        editor.putString(SHARED_KEY, text)
        editor.commit()
    }

    override fun clearText() {
        _localVariable = null
        editor.remove(SHARED_KEY)
        editor.commit()
    }

    override fun getText(): String {
        val localValue = getDataFromLocalVariable()
        val sharedPreferenceValue = getDataFromSharedPreference()

        if (localValue != null) {
            return localValue
        } else if (sharedPreferenceValue != null) {
            return sharedPreferenceValue
        } else {
            return ""
        }
    }

    companion object {
        private const val NAME_SHARED_PREFERENCE = "preference_name"

        private const val SHARED_KEY = "shared_key"

    }
}