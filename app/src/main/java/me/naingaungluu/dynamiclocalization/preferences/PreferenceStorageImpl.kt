package me.naingaungluu.dynamiclocalization.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class PreferenceStorageImpl @Inject constructor(
    @ActivityContext val context: Context
): PreferenceStorage {

    companion object {
        private const val SHARED_PREFERENCE_KEY = "SHARED_PREF_DYNAMIC_LOCALIZATION"
        private const val KEY_APP_LANGUAGE = "KEY_APP_LANGUAGE"
    }

    private val sharedPreference: SharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
    }

    override fun getAppLanguage(): AppLanguage {
        return try {
            val value = sharedPreference.getString(KEY_APP_LANGUAGE, "ENGLISH").orEmpty()
            AppLanguage.valueOf(value)
        } catch(e: Exception) {
            AppLanguage.ENGLISH
        }
    }

    override suspend fun saveAppLanguage(language: AppLanguage) {
        sharedPreference
            .edit()
            .putString(KEY_APP_LANGUAGE, language.name)
            .apply()
    }

}